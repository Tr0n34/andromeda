package fr.andromeda.sport.services.impl;

import fr.andromeda.sport.services.PythonScriptService;
import org.apache.catalina.connector.InputBuffer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class PythonSriptServiceImpl implements PythonScriptService {

    private static final Logger logger = LoggerFactory.getLogger(PythonSriptServiceImpl.class);;

    @Value("${script.process.timeout:3}")
    private long timeout;

    @Value("${script.name}")
    private String defaultScriptName;

    @Value("${script.path}")
    private String defaultScriptPath;

    @Value("${python.path:python}") // valeur par défaut "python"
    private String pythonExecutable;

    private final Map<String, Process> runningScripts;
    private final Map<String, Thread> readingThreads;

    @Autowired
    public PythonSriptServiceImpl() {
        runningScripts = new ConcurrentHashMap<>();
        readingThreads = new ConcurrentHashMap<>();
    }

    public String  startDefault() throws IOException {
        return start(defaultScriptName, defaultScriptPath);
    }

    public String start(String name, String path) throws IOException {
        if ( runningScripts.containsKey(name) && runningScripts.get(name).isAlive() ) {
            throw new RuntimeException("Impossible de démarrer. Le script est déjà démarré");
        }
        String processId = name + UUID.randomUUID();
        ProcessBuilder processBuilder =  new ProcessBuilder(pythonExecutable, Paths.get(path, name).toString());
        processBuilder.environment().put("PYTHONIOENCODING", "UTF-8");
        Process process = processBuilder
                .redirectErrorStream(true)
                .directory(new File(path))
                .start();
        runningScripts.put(processId, process);

        Thread readerThread = getThread(name, process, processId);
        readerThread.start();
        readingThreads.put(processId, readerThread);
        return processId;
    }

    public void stop(String processId) {
        Process process = runningScripts.get(processId);
        if ( process != null && process.isAlive() ) {
            process.destroy();
            checkDestroy(process);
            runningScripts.remove(processId);
            Thread readerThread = readingThreads.get(processId);
            logger.debug("Process {} destroyed", processId);
            if ( readerThread != null && readerThread.isAlive() ) {
                readerThread.interrupt();
                checkInterruption(readerThread);
                readingThreads.remove(processId);
                logger.debug("Thread {} interrupted", readerThread.getName());
            }
        }
    }

    @Override
    public void stopAll() {
        runningScripts.forEach( (processId, process) -> stop(processId));
    }

    @Override
    public Map<String, Process> getProcesses() {
        return runningScripts;
    }

    @Override
    public Map<String, Thread> getThreads() {
        return readingThreads;
    }

    private void checkDestroy(Process process) {
        try {
            if ( process.waitFor(timeout, TimeUnit.SECONDS) ) {
                logger.warn("Process {} did not terminate after 3 seconds, forcing destroyForcibly", process.pid());
                process.destroyForcibly();
            }
        } catch(InterruptedException interruptedException) {
            logger.error(interruptedException.getLocalizedMessage(), interruptedException);
        }
    }

    private void checkInterruption(Thread thread) {
        try {
            thread.join(2000);
            if (thread.isAlive() ) {
                logger.warn("Thread {} did not terminate after 2 seconds, forcing", thread.getName());
            }
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
            logger.error(interruptedException.getLocalizedMessage(), interruptedException);
        }
    }

    private static @NotNull Thread getThread(String name, Process process, String processId) {
        Thread readerThread = new Thread(
                () -> {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                        String line;
                        while ( (line = reader.readLine()) != null ) {
                            logger.debug("Script {} ::id={}:: {}", name, processId, line);
                        }
                    } catch (IOException ioException) {
                        logger.error(ioException.getLocalizedMessage(), ioException);
                    }
                }
        );
        readerThread.setName("PythonScriptReader-" + processId);
        return readerThread;
    }

}

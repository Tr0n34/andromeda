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

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Service
public class PythonSriptServiceImpl implements PythonScriptService {

    private static final Logger logger = LoggerFactory.getLogger(PythonSriptServiceImpl.class);

    @Value("${python.socket.port:9999}")
    private int tcpSocketPort;

    @Value("${python.socket.addr:127.0.0.1}")
    private String tcpSocketAddr;

    @Value("${script.process.timeout:3}")
    private long timeout;

    @Value("${script.name}")
    private String defaultScriptName;

    @Value("${script.path}")
    private String defaultScriptPath;

    @Value("${python.path:python}")
    private String pythonExecutable;

    @Value("${python.socket.ready}")
    private String flagSocketReady;

    private final Map<String, Process> runningScripts;
    private final Map<String, Thread> readingThreads;

    @Autowired
    public PythonSriptServiceImpl() {
        runningScripts = new ConcurrentHashMap<>();
        readingThreads = new ConcurrentHashMap<>();
    }

    public void waitForServerReady(long timeoutMs) throws InterruptedException, IOException {
        File flag = new File(flagSocketReady);
        long deadline = System.currentTimeMillis() + timeoutMs;

        while (!flag.exists()) {
            if (System.currentTimeMillis() > deadline) {
                throw new IOException("Timeout waiting for Python stop server readiness flag: " + flagSocketReady);
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                throw ie;
            }
        }
    }

    public void stopPythonScript(String host, int port, String data) throws IOException, InterruptedException {
        logger.debug("socket : {}:{}", host, port);
        int retries = 5;
        IOException lastException = null;

        logger.debug("Tentative de connexion à la socket {}:{}", host, port);

        while (retries > 0) {
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress(host, port), 2000); // timeout 2s
                try (PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
                    writer.println("STOP" + data);
                    logger.debug("Commande STOP envoyée au script Python");
                    return; // succès, on sort
                }
            } catch (IOException e) {
                lastException = e;
                retries--;
                logger.warn("Connexion refusée, tentative restante : {}", retries);
                Thread.sleep(500); // pause 500ms avant retry
            }
        }
        logger.error("Impossible de se connecter au script Python sur {}:{} après plusieurs tentatives", host, port, lastException);
        throw lastException;
    }

    public String  startDefault() throws IOException, InterruptedException {
        return start(defaultScriptName, defaultScriptPath);
    }

    public String start(String name, String path) throws IOException, InterruptedException {
        if ( runningScripts.containsKey(name) && runningScripts.get(name).isAlive() ) {
            throw new RuntimeException("Impossible de démarrer. Le script est déjà démarré");
        }
        String processId = name + UUID.randomUUID();
        ProcessBuilder processBuilder =  new ProcessBuilder(pythonExecutable, "-u", Paths.get(path, name).toString());
        processBuilder.environment().put("PYTHONIOENCODING", "UTF-8");
        Process process = processBuilder
                .redirectErrorStream(true)
                .directory(new File(path))
                .start();
        runningScripts.put(processId, process);

        Thread readerThread = getThread(name, process, processId);
        readerThread.start();
        readingThreads.put(processId, readerThread);
        waitForServerReady(5000);
        return processId;
    }

    public void stop(String processId) throws IOException, InterruptedException {
        stopPythonScript(tcpSocketAddr, tcpSocketPort, "");
        Process process = runningScripts.get(processId);
        process.waitFor();
        if ( process.isAlive() ) {
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
        runningScripts.forEach( (processId, process) -> {
            try {
                stop(processId);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
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

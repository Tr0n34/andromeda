package fr.andromeda.sport.services;

import java.io.IOException;
import java.util.Map;

public interface PythonScriptService {

    String startDefault() throws IOException, InterruptedException;

    String start(String name, String path) throws IOException, InterruptedException;

    void stop(String processId) throws IOException, InterruptedException;

    void stopAll();

    Map<String, Process> getProcesses();

    Map<String, Thread> getThreads();

    void stopPythonScript(String host, int port, String data)  throws IOException, InterruptedException;

}

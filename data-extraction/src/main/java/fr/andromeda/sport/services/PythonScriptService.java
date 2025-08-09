package fr.andromeda.sport.services;

import java.io.IOException;
import java.util.Map;

public interface PythonScriptService {

    String startDefault() throws IOException;

    String start(String name, String path) throws IOException;

    void stop(String processId);

    void stopAll();

    Map<String, Process> getProcesses();

    Map<String, Thread> getThreads();

}

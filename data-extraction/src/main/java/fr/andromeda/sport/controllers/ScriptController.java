package fr.andromeda.sport.controllers;

import fr.andromeda.sport.services.PythonScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("${api.prefix}/scripts")
public class ScriptController {

    private final PythonScriptService pythonScriptService;

    @Autowired
    public ScriptController(PythonScriptService pythonScriptService) {
        this.pythonScriptService = pythonScriptService;
    }

    @PostMapping
    public ResponseEntity<String> start() throws IOException, InterruptedException {
        String processId = pythonScriptService.startDefault();
        URI location = ServletUriComponentsBuilder.fromPath("/api/v1/scripts")
                .path("/{id}")
                .buildAndExpand(processId)
                .toUri();
        return ResponseEntity.created(location).body(processId);
    }

    @PostMapping(path = "/{id}/stop")
    public ResponseEntity<Void> stop(@PathVariable("id") String processId) throws IOException, InterruptedException {
        pythonScriptService.stop(processId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/processes")
    public ResponseEntity<Map<String, String>> getProcesses() {
        Map<String, Process> processMap = pythonScriptService.getProcesses();
        Map<String, String> convertedMap = new HashMap<>();
        processMap.forEach((processId, process) -> convertedMap.put(processId, String.valueOf(process.pid())) );
        return ResponseEntity.ok(convertedMap);
    }

}

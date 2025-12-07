package fr.andromeda.auth.controllers;

import fr.andromeda.api.enums.Urls;
import fr.andromeda.auth.services.impl.RegisteredClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/clients")
public class RegisteredClientController {

    RegisteredClientService registeredClientService;

    @Autowired
    public RegisteredClientController(RegisteredClientService registeredClientService) {
        this.registeredClientService = registeredClientService;
    }

    @PostMapping
    public ResponseEntity<Void> createRegisteredClient(@RequestBody RegisteredClient registeredClient) {
        return null;
    }

    @GetMapping("/clientId/{id}")
    public ResponseEntity<RegisteredClient> getRegisteredClient(@PathVariable String id) {
        return ResponseEntity.ok(registeredClientService.findByClientId(id));
    }

}

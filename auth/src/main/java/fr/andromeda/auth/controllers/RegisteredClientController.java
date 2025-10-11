package fr.andromeda.auth.controllers;

import fr.andromeda.api.enums.UrlPattern;
import fr.andromeda.auth.dto.authentication.RegisteredClientDTO;
import fr.andromeda.auth.services.impl.RegisteredClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/clients")
public class RegisteredClientController {

    private final RegisteredClientService registeredClientService;

    public RegisteredClientController(RegisteredClientService registeredClientService) {
        this.registeredClientService = registeredClientService;
    }

    @PostMapping
    public ResponseEntity<Void> createClient(@RequestBody RegisteredClientDTO clientDTO) {
        RegisteredClientDTO saved = registeredClientService.create(clientDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(UrlPattern.ID.getPath())
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{clientId}")
    public RegisteredClientDTO getClient(@PathVariable String clientId) {
        return registeredClientService.findByClientId(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
    }

    @GetMapping
    public List<RegisteredClientDTO> getAllClients() {
        return registeredClientService.findAll();
    }


}

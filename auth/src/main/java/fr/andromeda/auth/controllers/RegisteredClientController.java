package fr.andromeda.auth.controllers;

import fr.andromeda.auth.services.impl.RegisteredClientService;
import fr.andromeda.auth.services.interfaces.IRegistredClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${api.prefix}/clients")
public class RegisteredClientController {

    private final RegisteredClientService registeredClientService;

    public RegisteredClientController(RegisteredClientService registeredClientService) {
        this.registeredClientService = registeredClientService;
    }

    @PostMapping
    public RegisteredClient createClient(@RequestBody RegisteredClient client) {
        RegisteredClient clientWithId = RegisteredClient.withId(client.getId() != null ? client.getId() : UUID.randomUUID().toString())
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret())
                .clientAuthenticationMethods(auth -> auth.addAll(client.getClientAuthenticationMethods()))
                .authorizationGrantTypes(grants -> grants.addAll(client.getAuthorizationGrantTypes()))
                .scopes(scopes -> scopes.addAll(client.getScopes()))
                .redirectUris(uris -> uris.addAll(client.getRedirectUris()))
                .build();
        return registeredClientService.create(client);
    }

    @GetMapping("/{clientId}")
    public RegisteredClient getClient(@PathVariable String clientId) {
        return registeredClientService.findByClientId(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
    }

    @GetMapping
    public List<RegisteredClient> getAllClients() {
        return registeredClientService.findAll();
    }


}

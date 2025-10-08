package fr.andromeda.auth.services.impl;

import fr.andromeda.auth.entities.RegisteredClientEntity;
import fr.andromeda.auth.mappers.RegisteredClientMapper;
import fr.andromeda.auth.repositories.AuthRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegisteredClientService {

    private final AuthRegisteredClientRepository repository;
    private final RegisteredClientMapper mapper;

    public RegisteredClientService(AuthRegisteredClientRepository repository, RegisteredClientMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public RegisteredClient create(RegisteredClient client) {
        RegisteredClientEntity entity = mapper.toEntity(client);
        repository.save(entity);
        return client;
    }

    public Optional<RegisteredClient> findByClientId(String clientId) {
        return repository.findByClientId(clientId).map(mapper::toRegisteredClient);
    }

    public List<RegisteredClient> findAll() {
        return repository.findAll().stream().map(mapper::toRegisteredClient).toList();
    }

}

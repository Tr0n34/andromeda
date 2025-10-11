package fr.andromeda.auth.repositories;

import fr.andromeda.auth.entities.RegisteredClientEntity;
import fr.andromeda.auth.mappers.RegisteredClientConverter;
import fr.andromeda.auth.mappers.RegisteredClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

@Repository
public class RegisteredClientAdapterRepository implements RegisteredClientRepository {

    private final RegisteredClientConverter registeredClientConverter;
    private final AuthRegisteredClientRepository authRegisteredClientRepository;

    @Autowired
    public RegisteredClientAdapterRepository(AuthRegisteredClientRepository authRegisteredClientRepository,
                                             RegisteredClientConverter  registeredClientConverter) {
        this.authRegisteredClientRepository = authRegisteredClientRepository;
        this.registeredClientConverter = registeredClientConverter;
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        throw new UnsupportedOperationException("Save not implemented");
    }

    @Override
    public RegisteredClient findById(String id) {
        return authRegisteredClientRepository.findById(id)
                .map(registeredClientConverter::toSpringRegisteredClient)
                .orElse(null);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return authRegisteredClientRepository.findByClientId(clientId)
                .map(registeredClientConverter::toSpringRegisteredClient)
                .orElse(null);
    }

}
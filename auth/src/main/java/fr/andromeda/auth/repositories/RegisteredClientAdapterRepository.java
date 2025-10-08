package fr.andromeda.auth.repositories;

import fr.andromeda.auth.mappers.RegisteredClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Component;

@Component
public class RegisteredClientAdapterRepository implements RegisteredClientRepository {

    private final AuthRegisteredClientRepository authRegisteredClientRepository;
    private final RegisteredClientMapper clientMapper;

    @Autowired
    public RegisteredClientAdapterRepository(AuthRegisteredClientRepository authRegisteredClientRepository, RegisteredClientMapper clientMapper) {
        this.authRegisteredClientRepository = authRegisteredClientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
        public void save(RegisteredClient registeredClient) {
            throw new UnsupportedOperationException("Save not implemented");
        }

        @Override
        public RegisteredClient findById(String id) {
            return authRegisteredClientRepository.findById(id)
                    .map(clientMapper::toRegisteredClient)
                    .orElse(null);
        }

        @Override
        public RegisteredClient findByClientId(String clientId) {
            return authRegisteredClientRepository.findByClientId(clientId)
                    .map(clientMapper::toRegisteredClient)
                    .orElse(null);
        }
    }
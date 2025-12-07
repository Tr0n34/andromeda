package fr.andromeda.auth.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisteredClientService {

    private final PasswordEncoder passwordEncoder;
    private final RegisteredClientRepository registeredClientRepository;

    @Autowired
    public RegisteredClientService(PasswordEncoder passwordEncoder, RegisteredClientRepository registeredClientRepository) {
        this.passwordEncoder = passwordEncoder;
        this.registeredClientRepository = registeredClientRepository;
    }

    public void createRegisteredClient(RegisteredClient registeredClient) {
        registeredClientRepository.save(registeredClient);
    }

    public RegisteredClient findByClientId(String clientId) {
        return registeredClientRepository.findByClientId(clientId);
    }


}

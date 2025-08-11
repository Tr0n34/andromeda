package fr.andromeda.cyb.services.impl;

import fr.andromeda.cyb.dto.ActiveSubscriptionDTO;
import fr.andromeda.cyb.dto.ClientDTO;
import fr.andromeda.cyb.services.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Override
    public List<ClientDTO> findAll() {
        return List.of();
    }

    @Override
    public ClientDTO findById(Long id) {
        return null;
    }

    @Override
    public ClientDTO save(ClientDTO client) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ActiveSubscriptionDTO findByActiveSubscription(ClientDTO client) {
        return null;
    }

}

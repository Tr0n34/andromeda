package fr.andromeda.cyb.services;

import fr.andromeda.cyb.dto.ActiveSubscriptionDTO;
import fr.andromeda.cyb.dto.ClientDTO;

import java.util.List;

public interface ClientService {

    List<ClientDTO> findAll();

    ClientDTO findById(Long id);

    ClientDTO save(ClientDTO client);

    void delete(Long id);

    ActiveSubscriptionDTO findByActiveSubscription(ClientDTO client);

}

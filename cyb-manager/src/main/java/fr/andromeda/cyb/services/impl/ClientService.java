package fr.andromeda.cyb.services.impl;

import fr.andromeda.cyb.configurations.errors.ErrorProvider;
import fr.andromeda.cyb.dto.ActiveSubscriptionDTO;
import fr.andromeda.cyb.dto.ClientDTO;
import fr.andromeda.cyb.entites.Client;
import fr.andromeda.cyb.mappers.ClientMapper;
import fr.andromeda.cyb.repositories.ClientRepository;
import fr.andromeda.cyb.services.AbstractCrudService;
import fr.andromeda.cyb.services.interfaces.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends AbstractCrudService<ClientDTO, Client, ClientRepository, Long> implements IClientService {

    @Autowired
    public ClientService(ClientMapper clientMapper, ClientRepository clientRepository, ErrorProvider errorProvider) {
        super(clientMapper, clientRepository, Client.class.getSimpleName(), errorProvider);
    }

    @Override
    public ActiveSubscriptionDTO findByActiveSubscription(ClientDTO client) {
        return null;
    }

}

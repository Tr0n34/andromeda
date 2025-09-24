package fr.andromeda.cyb.services.interfaces;

import fr.andromeda.cyb.dto.ActiveSubscriptionDTO;
import fr.andromeda.cyb.dto.ClientDTO;

public interface IClientService {

    ActiveSubscriptionDTO findByActiveSubscription(ClientDTO client);

}

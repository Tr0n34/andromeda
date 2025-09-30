package fr.andromeda.cyb.services.interfaces;

import fr.andromeda.cyb.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {

    UserDTO loadUserByUsername(String username);

}

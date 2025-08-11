package fr.andromeda.cyb.services;

import fr.andromeda.cyb.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    UserDTO loadUserByUsername(String username) throws UsernameNotFoundException;

}

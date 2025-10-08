package fr.andromeda.cyb.services.interfaces;

import fr.andromeda.api.exceptions.ResourceNotFoundException;
import fr.andromeda.api.services.interfaces.ICrudService;
import fr.andromeda.cyb.dto.UserDTO;
import fr.andromeda.cyb.entites.Role;
import fr.andromeda.cyb.entites.User;
import fr.andromeda.cyb.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface IUserService extends UserDetailsService, ICrudService<UserDTO, User, UserRepository, Long> {

    UserDTO loadUserByUsername(String username);

    Set<Role> resolveRolesFromDto(UserDTO userDTO) throws ResourceNotFoundException;

    void lock(Long id);

    void unlock(Long id);

}

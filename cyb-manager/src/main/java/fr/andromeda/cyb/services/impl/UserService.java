package fr.andromeda.cyb.services.impl;

import fr.andromeda.cyb.batch.errors.ErrorPopulator;
import fr.andromeda.cyb.configurations.errors.ErrorProvider;
import fr.andromeda.cyb.dto.UserDTO;
import fr.andromeda.cyb.entites.User;
import fr.andromeda.cyb.exceptions.ResourceNotFoundException;
import fr.andromeda.cyb.mappers.UserMapper;
import fr.andromeda.cyb.repositories.UserRepository;
import fr.andromeda.cyb.services.AbstractCrudService;
import fr.andromeda.cyb.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.Instant;

@Service
public class UserService extends AbstractCrudService<UserDTO, User, UserRepository, Long> implements IUserService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, ErrorProvider errorProvider) {
        super(userMapper, userRepository, User.class, errorProvider);
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO create(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return super.create(userDTO);
    }

    public void lock(Long id) {

    }

    @Transactional
    @Override
    public UserDTO loadUserByUsername(String username) {
        try {
            return getMapper().toDto(getRepository().findByUsernameWithRoles(username).orElseThrow(() -> getErrorProvider().notFound(User.class)));
        } catch (ResourceNotFoundException e) {
            throw new UsernameNotFoundException(username);
        }
    }

    public boolean isExpired(Jwt token) {
        return token != null && token.getExpiresAt() != null && token.getExpiresAt().isAfter(Instant.now());
    }

}

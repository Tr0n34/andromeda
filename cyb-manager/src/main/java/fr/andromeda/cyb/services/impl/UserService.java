package fr.andromeda.cyb.services.impl;

import fr.andromeda.cyb.configurations.errors.ErrorProvider;
import fr.andromeda.cyb.dto.RoleDTO;
import fr.andromeda.cyb.dto.UserDTO;
import fr.andromeda.cyb.entites.Role;
import fr.andromeda.cyb.entites.User;
import fr.andromeda.cyb.exceptions.ResourceNotFoundException;
import fr.andromeda.cyb.mappers.UserMapper;
import fr.andromeda.cyb.repositories.RoleRepository;
import fr.andromeda.cyb.repositories.UserRepository;
import fr.andromeda.cyb.services.AbstractCrudService;
import fr.andromeda.cyb.services.business.UserValidatorService;
import fr.andromeda.cyb.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService extends AbstractCrudService<UserDTO, User, UserRepository, Long> implements IUserService {

    private final PasswordEncoder passwordEncoder;
    private final UserValidatorService userValidatorService;
    private final RoleService roleService;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleService roleService,
                       UserValidatorService userValidatorService,
                       UserMapper userMapper,
                       PasswordEncoder passwordEncoder,
                       ErrorProvider errorProvider, UserRepository userRepository1) {
        super(userMapper, userRepository, User.class.getSimpleName(), errorProvider);
        this.userValidatorService = userValidatorService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO create(UserDTO userDTO) throws ResourceNotFoundException {
        User newUser = getMapper().toEntity(userDTO);
        newUser.setRoles(Collections.emptySet());
        List<RoleDTO> roles = roleService.findAll();
        userValidatorService.validateAuthoritiesExist(userDTO.getRoles(), roles);
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Set<RoleDTO> rolesToAssign = userDTO.getRoles().stream()
                .map(dto -> roles.stream()
                        .filter(r -> r.getAuthority().equals(dto.getAuthority()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Role not found")))
                .collect(Collectors.toSet());
        userDTO.setRoles(rolesToAssign);
        return getMapper().toDto(userRepository.save(newUser));
    }

    public void lock(Long id) {

    }

    @Transactional
    @Override
    public UserDTO loadUserByUsername(String username) {
        try {
            return getMapper().toDto(getRepository().findByUsernameWithRoles(username).orElseThrow(() -> getErrorProvider().notFound(User.class.getSimpleName())));
        } catch (ResourceNotFoundException e) {
            throw new UsernameNotFoundException(username);
        }
    }

    public boolean isExpired(Jwt token) {
        return token != null && token.getExpiresAt() != null && token.getExpiresAt().isAfter(Instant.now());
    }

}

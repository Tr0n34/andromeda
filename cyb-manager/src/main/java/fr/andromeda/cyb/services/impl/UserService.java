package fr.andromeda.cyb.services.impl;

import fr.andromeda.api.errors.ErrorProvider;
import fr.andromeda.api.exceptions.ResourceNotFoundException;
import fr.andromeda.api.services.interfaces.AbstractCrudService;
import fr.andromeda.cyb.dto.RoleDTO;
import fr.andromeda.cyb.dto.UserDTO;
import fr.andromeda.cyb.entites.Role;
import fr.andromeda.cyb.entites.User;
import fr.andromeda.cyb.mappers.UserMapper;
import fr.andromeda.cyb.repositories.UserRepository;
import fr.andromeda.cyb.services.business.UserValidatorService;
import fr.andromeda.cyb.services.interfaces.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService extends AbstractCrudService<UserDTO, User, UserRepository, Long> implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

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
                       ErrorProvider errorProvider) {
        super(userMapper, userRepository, User.class.getSimpleName(), errorProvider);
        this.userValidatorService = userValidatorService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO create(UserDTO userDTO) throws ResourceNotFoundException {
        User newUser = getMapper().toEntity(userDTO);
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        newUser.setRoles(resolveRolesFromDto(userDTO));
        return getMapper().toDto(userRepository.save(newUser));
    }

    @Override
    public void patch(Long id, UserDTO userDTO) throws ResourceNotFoundException {
        User user = loadEntity(id);
        getMapper().patchFromDto(userDTO, user);
        if ( userDTO.getRoles() != null ) {
            user.setRoles(resolveRolesFromDto(userDTO));
            userRepository.save(user);
        } else {
            super.patch(id, userDTO);
        }
    }

    @Override
    public Set<Role> resolveRolesFromDto(UserDTO userDTO) throws ResourceNotFoundException {
        return userDTO.getRoles().stream()
                .map(r -> roleService.getRepository()
                        .findByAuthority(r.getAuthority())
                        .orElseThrow(() -> getErrorProvider().notFound(Role.class.getSimpleName())))
                .collect(Collectors.toSet());
    }

    @Override
    public void lock(Long id) {
        super.patch(id, locked(true));
    }

    @Override
    public void unlock(Long id) {
        super.patch(id, locked(false));
    }

    private UserDTO locked(boolean lock) {
        return new UserDTO().setLocked(lock);
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

    private void attachEntityRoleTo(User user, Set<RoleDTO> roles) {
        user.setRoles(
                roles.stream()
                        .map(r -> roleService.getRepository().findByAuthority(r.getAuthority()).orElseThrow(() -> new RuntimeException("Role not found")))
                        .collect(Collectors.toSet())
        );
    }

    private void detachEntityRoleFrom(User user) {
        user.setRoles(Collections.emptySet());
        logger.debug("Role Entity detached from User Entity to avoid creation of a new Role");
    }

    private Set<RoleDTO> assignRoles(UserDTO userDTO, List<RoleDTO> availableRoles) {
        return userDTO.getRoles().stream()
                .map(dto -> availableRoles.stream()
                        .filter(r -> r.getAuthority().equals(dto.getAuthority()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Role not found")))
                .collect(Collectors.toSet());
    }

}

package fr.andromeda.cyb.services.impl;

import fr.andromeda.cyb.dto.UserDTO;
import fr.andromeda.cyb.entites.User;
import fr.andromeda.cyb.mappers.UserMapper;
import fr.andromeda.cyb.repositories.UserRepository;
import fr.andromeda.cyb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.time.Instant;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO create(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDTO)));
    }

    public void lock(Long id) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);

    }

    @Transactional
    @Override
    public UserDTO loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameWithRoles(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return userMapper.toDto(user);
    }

    public boolean isExpired(Jwt token) {
        return token != null && token.getExpiresAt() != null && token.getExpiresAt().isAfter(Instant.now());
    }

}

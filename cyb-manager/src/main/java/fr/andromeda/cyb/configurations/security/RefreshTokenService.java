package fr.andromeda.cyb.configurations.security;

import fr.andromeda.cyb.dto.UserDTO;
import fr.andromeda.cyb.dto.authentification.RefreshTokenDTO;
import fr.andromeda.cyb.entites.User;
import fr.andromeda.cyb.entites.authentication.RefreshToken;
import fr.andromeda.cyb.mappers.RefreshTokenMapper;
import fr.andromeda.cyb.mappers.UserMapper;
import fr.andromeda.cyb.repositories.RefreshTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class RefreshTokenService {

    private static final Logger logger = LoggerFactory.getLogger(RefreshTokenService.class);

    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenMapper refreshTokenMapper;
    private final UserMapper userMapper;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder encoder;

    @Autowired
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository,
                               RefreshTokenMapper refreshTokenMapper,
                               UserMapper userMapper,
                               JwtTokenService jwtTokenService,
                               PasswordEncoder encoder) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.refreshTokenMapper = refreshTokenMapper;
        this.jwtTokenService = jwtTokenService;
        this.userMapper = userMapper;
        this.encoder = encoder;
    }

    public RefreshTokenDTO registerToken(UserDTO userDTO, Jwt token) {
        User user = userMapper.toEntity(userDTO);
        RefreshToken savedToken = refreshTokenRepository
                .findByUserAndRevokedFalseAndExpiresAtAfter(user, Instant.now())
                .map(existingToken -> {
                    existingToken.setRevoked(true);
                    logger.debug("RefreshToken {} already exists for userId: {} - Revoked in progress",
                            existingToken.getTokenHash(), user.getId());
                    refreshTokenRepository.save(existingToken);
                    return createToken(user, token);
                })
                .orElseGet(() -> createToken(user, token));
        return refreshTokenMapper.toDto(savedToken);
    }

    public RefreshTokenDTO getRefreshToken(UserDTO userDTO, Jwt token) {
        User user = userMapper.toEntity(userDTO);
        RefreshToken refreshToken = refreshTokenRepository.findByUserAndRevokedFalseAndExpiresAtAfter(user, Instant.now())
                .orElseThrow(
                        () ->  new RuntimeException("RefreshToken not found or revoked for userId: " + user.getId())
                );
        return refreshTokenMapper.toDto(refreshToken);
    }

    public RefreshTokenDTO validateToken(String refreshTokenValue) {
        return refreshTokenMapper.toDto(refreshTokenRepository.findAll().stream()
                .filter(t -> !t.isRevoked() && t.getExpiresAt().isAfter(Instant.now()))
                .filter(t -> encoder.matches(refreshTokenValue, t.getTokenHash()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Refresh token invalid or expired")));
    }

    private RefreshToken createToken(User user, Jwt token) {
        return refreshTokenRepository.save(new RefreshToken()
                .setUser(user)
                .setTokenHash(encoder.encode(token.getTokenValue()))
                .setExpiresAt(token.getExpiresAt())
                .setRevoked(false));
    }

    public void purgeRevokedTokens() {
        logger.debug("Purging revoked refresh tokens");
        refreshTokenRepository.deleteByRevokedTrue();
    }

    public boolean isExpired(RefreshTokenDTO refreshTokenDTO) {
        return refreshTokenDTO.getExpiresAt().isAfter(Instant.now());
    }

    public boolean isExpired(String rawToken) {
        RefreshToken refreshToken = refreshTokenRepository.findByTokenHashAndRevokedFalse(encoder.encode(rawToken)).orElseThrow();
        return refreshToken.getExpiresAt().isAfter(Instant.now());
    }

    public void revoke(Long id) {
        RefreshToken refreshToken = refreshTokenRepository.findById(id).orElseThrow(() -> new RuntimeException("RefreshToken not found"));
        refreshToken.setRevoked(true);
        refreshTokenRepository.save(refreshToken);
    }

    public RefreshTokenDTO rotate(Long id, Duration validityDuration) {
        revoke(id);
        RefreshToken oldRefreshToken = refreshTokenRepository.findById(id).orElseThrow(() -> new RuntimeException("RefreshToken not found"));
        RefreshTokenDTO refreshTokenDTO = refreshTokenMapper.toDto(oldRefreshToken);
        Jwt newRefreshToken = jwtTokenService.generate(refreshTokenDTO.getUser().getUsername(), validityDuration, refreshTokenDTO.getUser().getRoles());
        refreshTokenDTO.setToken(newRefreshToken.getTokenValue());
        createToken(oldRefreshToken.getUser(), newRefreshToken);
        return refreshTokenDTO;
    }

    public boolean checkNearRotation(RefreshTokenDTO refreshTokenDTO, Duration beforeRotation) {
        return Instant.now().isAfter(refreshTokenDTO.getExpiresAt().minusSeconds(beforeRotation.getSeconds()));
    }

}
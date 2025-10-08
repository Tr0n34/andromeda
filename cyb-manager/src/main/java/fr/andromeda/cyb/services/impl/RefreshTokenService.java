package fr.andromeda.cyb.services.impl;

import fr.andromeda.api.exceptions.ResourceNotFoundException;
import fr.andromeda.api.services.interfaces.AbstractCrudService;
import fr.andromeda.cyb.dto.UserDTO;
import fr.andromeda.cyb.dto.authentication.RefreshTokenDTO;
import fr.andromeda.cyb.entites.User;
import fr.andromeda.cyb.entites.auth.RefreshToken;
import fr.andromeda.cyb.mappers.RefreshTokenMapper;
import fr.andromeda.cyb.mappers.UserMapper;
import fr.andromeda.cyb.repositories.RefreshTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;

@Service
public class RefreshTokenService extends AbstractCrudService<RefreshTokenDTO, RefreshToken, RefreshTokenRepository, Long> {

    private static final Logger logger = LoggerFactory.getLogger(RefreshTokenService.class);

    private final UserMapper userMapper;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder encoder;

    @Autowired
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository,
                               RefreshTokenMapper refreshTokenMapper,
                               UserMapper userMapper,
                               JwtTokenService jwtTokenService,
                               PasswordEncoder encoder) {
        super(refreshTokenMapper, refreshTokenRepository, RefreshToken.class.getSimpleName());
        this.jwtTokenService = jwtTokenService;
        this.userMapper = userMapper;
        this.encoder = encoder;
    }

    public RefreshTokenDTO registerToken(UserDTO userDTO, String token) {
        User user = userMapper.toEntity(userDTO);
        RefreshToken savedToken = getRepository()
                .findByUserAndRevokedFalseAndExpiresAtAfter(user, Instant.now())
                .map(existingToken -> {
                    existingToken.setRevoked(true);
                    logger.debug("RefreshToken {} already exists for userId: {} - Revoked in progress",
                            existingToken.getTokenHash(), user.getId());
                    getRepository().save(existingToken);
                    return createToken(user, token);
                })
                .orElseGet(() -> createToken(user, token));
        return getMapper().toDto(savedToken);
    }

    public RefreshTokenDTO getRefreshToken(UserDTO userDTO, Jwt token) {
        User user = userMapper.toEntity(userDTO);
        RefreshToken refreshToken = getRepository().findByUserAndRevokedFalseAndExpiresAtAfter(user, Instant.now())
                .orElseThrow(
                        () ->  new RuntimeException("RefreshToken not found or revoked for userId: " + user.getId())
                );
        return getMapper().toDto(refreshToken);
    }

    public RefreshTokenDTO validateToken(String refreshTokenValue) {
        return getMapper().toDto(getRepository().findAll().stream()
                .filter(t -> !t.isRevoked() && t.getExpiresAt().isAfter(Instant.now()))
                .filter(t -> encoder.matches(refreshTokenValue, t.getTokenHash()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Refresh token invalid or expired")));
    }

    private RefreshToken createToken(User user, String token) {
        return getRepository().save(new RefreshToken()
                .setUser(user)
                .setIssuedAt(Instant.now())
                .setTokenHash(hashToken(token))
                .setExpiresAt(Instant.now().plus(Duration.ofDays(7)))
                .setRevoked(false));
    }

    private String hashToken(String token) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(token.getBytes(StandardCharsets.UTF_8));
            String base64Hash = Base64.getEncoder().encodeToString(hash);
            return encoder.encode(base64Hash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void purgeRevokedTokens() {
        logger.debug("Purging revoked refresh tokens");
        getRepository().deleteByRevokedTrue();
    }

    public boolean isExpired(RefreshTokenDTO refreshTokenDTO) {
        return refreshTokenDTO.getExpiresAt().isAfter(Instant.now());
    }

    public boolean isExpired(String rawToken) {
        RefreshToken refreshToken = getRepository().findByTokenHashAndRevokedFalse(encoder.encode(rawToken)).orElseThrow();
        return refreshToken.getExpiresAt().isAfter(Instant.now());
    }

    public void revoke(Long id) {
        RefreshToken refreshToken = getRepository().findById(id).orElseThrow();
        refreshToken.setRevoked(true);
        getRepository().save(refreshToken);
    }

    public RefreshTokenDTO rotate(Long id, Duration validityDuration) throws ResourceNotFoundException {
        revoke(id);
        RefreshToken oldRefreshToken = getRepository().findById(id).orElseThrow(() -> getErrorProvider().notFound(RefreshToken.class.getSimpleName()));
        RefreshTokenDTO refreshTokenDTO = getMapper().toDto(oldRefreshToken);
        //Jwt newRefreshToken = jwtTokenService.generate(refreshTokenDTO.getUser().getUsername(), validityDuration, refreshTokenDTO.getUser().getRoles());
        //refreshTokenDTO.setToken(newRefreshToken.getTokenValue());
        //createToken(oldRefreshToken.getUser(), newRefreshToken);
        return refreshTokenDTO;
    }

    public boolean checkNearRotation(RefreshTokenDTO refreshTokenDTO, Duration beforeRotation) {
        return Instant.now().isAfter(refreshTokenDTO.getExpiresAt().minusSeconds(beforeRotation.getSeconds()));
    }

}
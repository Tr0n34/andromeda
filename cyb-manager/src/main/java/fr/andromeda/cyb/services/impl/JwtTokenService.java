package fr.andromeda.cyb.services.impl;

import fr.andromeda.cyb.dto.RoleDTO;
import jakarta.servlet.http.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.*;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;

public class JwtTokenService {


    private static final Logger logger = LoggerFactory.getLogger(JwtTokenService.class);

    public static final Duration ACCESS_TOKEN_VALID_MS = Duration.ofSeconds(1000L * 60 * 15);
    public static final Duration REFRESH_TOKEN_VALID_MS = Duration.ofSeconds(1000L * 60 * 60 * 24 * 7);

    public static final String ALGORITHM = "HmacSHA256";
    public static final String CLAIM_ROLES = "roles";
    public static final String ISSUER = "self";
    public static final String REFRESH_TOKEN_COOKIE_NAME = "refresh_token";

    private final JwtDecoder jwtDecoder;
    private final JwtEncoder jwtEncoder;

    public JwtTokenService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }

    public JwtDecoder getJwtDecoder() {
        return jwtDecoder;
    }

    public JwtEncoder getJwtEncoder() {
        return jwtEncoder;
    }

    public Jwt decode(String token) {
        return jwtDecoder.decode(token);
    }

    public Jwt generate(String username, Duration validityDuration, Set<RoleDTO> roles) {
        checkUsername(username);
        checkRoles(roles);
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(ISSUER)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(validityDuration.toSeconds()))
                .subject(username)
                .claim(CLAIM_ROLES, roles)
                .build();
        logger.debug("JWT Token expired At {}", claims.getExpiresAt());
        return jwtEncoder.encode(JwtEncoderParameters.from(claims));
    }

    public void checkRoles(Set<RoleDTO> roles) {
        if ( roles == null || roles.isEmpty() ) {
            throw new AuthorizationDeniedException("no role are defined (null or empty)");
        }
    }

    public void checkUsername(String username) {
        if ( username == null || username.isEmpty() ) {
            throw new UsernameNotFoundException("username : {} cannot be null or empty");
        }
    }

    public boolean isValid(String secret, String token) {
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        JwtDecoder decoder = NimbusJwtDecoder.withSecretKey(secretKey).build();
        return decoder.decode(token) != null;
    }


    public Cookie secureCookieFrom(Jwt token) {
        Cookie cookie = new Cookie(REFRESH_TOKEN_COOKIE_NAME, token.getTokenValue());
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/api/v1/auth/refresh");
        cookie.setMaxAge(REFRESH_TOKEN_VALID_MS.getNano() / 1000);
        cookie.setAttribute("SameSite", "Strict");
        return cookie;
    }

}

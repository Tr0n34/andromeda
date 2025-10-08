package fr.andromeda.cyb.services.impl;

import fr.andromeda.cyb.dto.RoleDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JwtTokenService {

    @Value("${api.jwt.access-token-validity-ms:900000}")
    private long accessTokenValidityMs;
    @Value("${api.jwt.refresh-token-validity-ms:604800000}")
    private long refreshTokenValidityMs;

    private final SecretKey key;

    @Autowired
    public JwtTokenService(@Value("${api.jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(String username, Set<RoleDTO> roles) {
        Map<String, Object> claims = Map.of(
                "roles", roles.stream().map(RoleDTO::getAuthority).collect(Collectors.toList())
        );
        return generateToken(claims, username, accessTokenValidityMs);
    }

    public String generateRefreshToken(String username) {
        return generateToken(Map.of(), username, refreshTokenValidityMs);
    }

    private String generateToken(Map<String, Object> claims, String subject, long validityMs) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(now))
                .expiration(new Date(now + validityMs))
                .signWith(key)
                .compact();
    }

    public boolean isValid(String token) {
        boolean isValid = false;
        decode(token);
        return isValid;
    }

    public Claims decode(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsername(String token) {
        return decode(token).getSubject();
    }

    public Set<String> getRoles(String token) {
        Object roles = decode(token).get("roles");
        if (roles instanceof java.util.List<?> list) {
            return list.stream()
                    .filter(String.class::isInstance)
                    .map(String.class::cast)
                    .collect(Collectors.toSet());
        }
        return Set.of();
    }

}

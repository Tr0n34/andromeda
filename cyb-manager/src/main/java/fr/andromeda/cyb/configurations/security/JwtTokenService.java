package fr.andromeda.cyb.configurations.security;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import fr.andromeda.cyb.dto.RoleDTO;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;

@Component
public class JwtTokenService {


    private static final Logger logger = LoggerFactory.getLogger(JwtTokenService.class);

    public static final String ALGORITHM = "HmacSHA256";
    public static final String CLAIM_ROLES = "roles";
    public static final String ISSUER = "sefl";

    private final JwtDecoder jwtDecoder;
    private final JwtEncoder jwtEncoder;

    @Autowired
    public JwtTokenService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }

    public Jwt decode(String token) {
        return jwtDecoder.decode(token);
    }

    public Jwt generate(String username, Duration validityDuration, Collection<RoleDTO> roles) {
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
        return jwtEncoder.encode(JwtEncoderParameters.from(claims));
    }

    public void checkRoles(Collection<RoleDTO> roles) {
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

}

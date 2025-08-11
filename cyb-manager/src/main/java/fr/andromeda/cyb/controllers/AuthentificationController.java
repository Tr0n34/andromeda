package fr.andromeda.cyb.controllers;

import fr.andromeda.cyb.configurations.security.JwtTokenService;
import fr.andromeda.cyb.dto.authentification.RefreshTokenDTO;
import fr.andromeda.cyb.dto.authentification.TokenAuthenticationDTO;
import fr.andromeda.cyb.dto.authentification.UserAuthenticationDTO;
import fr.andromeda.cyb.dto.UserDTO;
import fr.andromeda.cyb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("${api.prefix}/auth")
public class AuthentificationController {

    private final Duration ACCESS_TOKEN_VALID_MS = Duration.ofSeconds(1000L * 60 * 15);
    private final Duration REFRESH_TOKEN_VALID_MS = Duration.ofSeconds(1000L * 60 * 60 * 24 * 7);

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenService jwtTokenService;

    @Autowired
    public AuthentificationController(AuthenticationManager authenticationManager, UserService userService, JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserAuthenticationDTO userAuthenticationDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAuthenticationDTO.getUsername(), userAuthenticationDTO.getPassword()));
        UserDTO userDTO = userService.loadUserByUsername(userAuthenticationDTO.getUsername());
        String access = jwtTokenService.generate(userAuthenticationDTO.getUsername(), ACCESS_TOKEN_VALID_MS, userDTO.getAuthorities()).getTokenValue();
        String refresh = jwtTokenService.generate(userAuthenticationDTO.getUsername(), REFRESH_TOKEN_VALID_MS, null).getTokenValue();
        return ResponseEntity.ok(Map.of("accessToken", access, "refreshToken", refresh, "roles", userDTO.getAuthorities()));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refresh(@RequestBody RefreshTokenDTO refreshTokenDTO) {
        String refreshToken = refreshTokenDTO.getRefreshToken();
        Jwt jwt = jwtTokenService.decode(refreshToken);
        String username = jwt.getSubject();
        Instant expiration = jwt.getExpiresAt();
        if ( expiration != null && expiration.isBefore(Instant.now()) ) {
            return ResponseEntity.status(401).body("Refresh token expired");
        }
        UserDTO userDTO = userService.loadUserByUsername(username);
        String newAccess = jwtTokenService.generate(username, ACCESS_TOKEN_VALID_MS, userDTO.getAuthorities()).getTokenValue();
        String newRefresh = jwtTokenService.generate(username, REFRESH_TOKEN_VALID_MS, null).getTokenValue();
        TokenAuthenticationDTO tokenAuthenticationDTO = new TokenAuthenticationDTO()
                .setRefreshToken(newRefresh)
                .setToken(newAccess)
                .setRoles(jwt.getClaim(JwtTokenService.CLAIM_ROLES));
        return ResponseEntity.ok(tokenAuthenticationDTO);
    }

}

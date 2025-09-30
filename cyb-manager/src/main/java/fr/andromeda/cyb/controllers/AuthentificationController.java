package fr.andromeda.cyb.controllers;

import fr.andromeda.cyb.services.impl.JwtTokenService;
import fr.andromeda.cyb.services.impl.RefreshTokenService;
import fr.andromeda.cyb.dto.UserDTO;
import fr.andromeda.cyb.dto.authentication.TokenAuthenticationDTO;
import fr.andromeda.cyb.dto.authentication.UserAuthenticationDTO;
import fr.andromeda.cyb.exceptions.ResourceNotFoundException;
import fr.andromeda.cyb.services.interfaces.IUserService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/auth")
public class AuthentificationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthentificationController.class);

    private final AuthenticationManager authenticationManager;
    private final IUserService userService;
    private final JwtTokenService jwtTokenService;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public AuthentificationController(AuthenticationManager authenticationManager,
                                      IUserService userService,
                                      RefreshTokenService refreshTokenService,
                                      JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.refreshTokenService = refreshTokenService;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenAuthenticationDTO> login(@RequestBody UserAuthenticationDTO userAuthenticationDTO, HttpServletResponse response) throws ResourceNotFoundException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userAuthenticationDTO.getUsername(),
                userAuthenticationDTO.getPassword()));
        UserDTO userDTO = userService.loadUserByUsername(userAuthenticationDTO.getUsername());
        Jwt access = jwtTokenService.generate(userAuthenticationDTO.getUsername(), JwtTokenService.ACCESS_TOKEN_VALID_MS, userDTO.getRoles());
        Jwt refresh = jwtTokenService.generate(userAuthenticationDTO.getUsername(), JwtTokenService.REFRESH_TOKEN_VALID_MS, null);
        refreshTokenService.registerToken(userDTO, refresh);
        response.addCookie(jwtTokenService.secureCookieFrom(refresh));
        TokenAuthenticationDTO tokenAuthenticationDTO = new TokenAuthenticationDTO()
                .setToken(access.getTokenValue())
                .setRefreshToken(refresh.getTokenValue())
                .setRoles(access.getClaim(JwtTokenService.CLAIM_ROLES));
        logger.debug("TokenAuthenticationDTO: {}", tokenAuthenticationDTO);
        return ResponseEntity.ok(tokenAuthenticationDTO);
    }

    @GetMapping("/refresh")
    public ResponseEntity<TokenAuthenticationDTO> getNewAccessToken(@RequestParam String refreshTokenValue) {
        refreshTokenService.validateToken(refreshTokenValue);
        Jwt refreshToken = jwtTokenService.decode(refreshTokenValue);
        Jwt access = jwtTokenService.generate(refreshToken.getSubject(), JwtTokenService.ACCESS_TOKEN_VALID_MS, refreshToken.getClaim(JwtTokenService.CLAIM_ROLES));
        TokenAuthenticationDTO tokenAuthenticationDTO = new TokenAuthenticationDTO()
                .setToken(access.getTokenValue())
                .setRefreshToken(refreshToken.getTokenValue())
                .setRoles(access.getClaim(JwtTokenService.CLAIM_ROLES));
        logger.debug("refreshToken: {}", refreshToken.getTokenValue());
        return ResponseEntity.ok(tokenAuthenticationDTO);
    }

}

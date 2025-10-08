package fr.andromeda.cyb.controllers;

import fr.andromeda.api.exceptions.ResourceNotFoundException;
import fr.andromeda.cyb.dto.UserDTO;
import fr.andromeda.cyb.dto.authentication.TokenAuthenticationDTO;
import fr.andromeda.cyb.dto.authentication.UserAuthenticationDTO;
import fr.andromeda.cyb.services.impl.JwtTokenService;
import fr.andromeda.cyb.services.impl.RefreshTokenService;
import fr.andromeda.cyb.services.interfaces.IUserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
        String access = jwtTokenService.generateAccessToken(userAuthenticationDTO.getUsername(), userDTO.getRoles());
        String refresh = jwtTokenService.generateRefreshToken(userAuthenticationDTO.getUsername());
        refreshTokenService.registerToken(userDTO, refresh);
        //response.addCookie(jwtTokenService.secureCookieFrom(refresh));
        TokenAuthenticationDTO tokenAuthenticationDTO = new TokenAuthenticationDTO()
                .setToken(access)
                .setRefreshToken(refresh)
                .setRoles(userDTO.getRoles());
        logger.debug("TokenAuthenticationDTO: {}", tokenAuthenticationDTO);
        return ResponseEntity.ok(tokenAuthenticationDTO);
    }

    @GetMapping("/refresh")
    public ResponseEntity<TokenAuthenticationDTO> getNewAccessToken(@RequestParam String refreshToken) {
        refreshTokenService.validateToken(refreshToken);
        Claims claims = jwtTokenService.decode(refreshToken);
        /*String access = jwtTokenService.generateAccessToken(claims.getSubject());
        TokenAuthenticationDTO tokenAuthenticationDTO = new TokenAuthenticationDTO()
                .setToken(access.getTokenValue())
                .setRefreshToken(refreshToken.getTokenValue())
                .setRoles(access.getClaim(JwtTokenService.CLAIM_ROLES));
        logger.debug("refreshToken: {}", refreshToken.getTokenValue());*/
        return ResponseEntity.ok(null);
    }

}

package fr.andromeda.cyb.configurations.security;

import org.springframework.beans.factory.annotation.Value;

public class JwtConfiguration {

    @Value("${jwt.tokens.refresh.duration}")
    private Long refreshTokenDuration;
    @Value("${jwt.tokens.access.duration}")
    private Long accessTokenDuration;


}

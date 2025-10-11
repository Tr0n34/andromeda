package fr.andromeda.auth.services.impl;

import fr.andromeda.auth.entities.OAuth2AuthorizationEntity;
import fr.andromeda.auth.entities.RegisteredClientEntity;
import fr.andromeda.auth.mappers.OAuth2AuthorizationMapper;
import fr.andromeda.auth.mappers.RegisteredClientConverter;
import fr.andromeda.auth.mappers.RegisteredClientMapper;
import fr.andromeda.auth.repositories.OAuth2AuthorizationRepository;
import fr.andromeda.auth.repositories.AuthRegisteredClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
public class JpaOAuth2AuthorizationService implements OAuth2AuthorizationService {

    private final OAuth2AuthorizationRepository oAuth2AuthorizationRepository;
    private final OAuth2AuthorizationMapper authorizationMapper;
    private final AuthRegisteredClientRepository registeredClientRepository;
    private final RegisteredClientConverter registeredClientConverter;

    @Autowired
    public JpaOAuth2AuthorizationService(AuthRegisteredClientRepository registeredClientRepository,
                                         RegisteredClientConverter registeredClientConverter,
                                         OAuth2AuthorizationRepository oAuth2AuthorizationRepository,
                                         OAuth2AuthorizationMapper authorizationMapper) {
        this.oAuth2AuthorizationRepository = oAuth2AuthorizationRepository;
        this.authorizationMapper = authorizationMapper;
        this.registeredClientRepository = registeredClientRepository;
        this.registeredClientConverter = registeredClientConverter;
    }

    @Override
    public void save(OAuth2Authorization authorization) {
        RegisteredClientEntity registeredClientEntity = registeredClientRepository.findByClientId(authorization.getRegisteredClientId())
                .orElseThrow(() -> new RuntimeException("Resource not found"));
        oAuth2AuthorizationRepository.save(authorizationMapper.toEntity(authorization, registeredClientEntity));
    }

    @Override
    public void remove(OAuth2Authorization authorization) {
        oAuth2AuthorizationRepository.deleteById(authorization.getId());
    }

    @Override
    public OAuth2Authorization findById(String id) {
        OAuth2AuthorizationEntity entity = oAuth2AuthorizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));
        RegisteredClientEntity registeredClientEntity = registeredClientRepository.findByClientId(entity.getRegisteredClient().getClientId())
                .orElseThrow(() -> new RuntimeException("RegisteredClient not found"));
        return authorizationMapper.toAuthorization(entity, registeredClientEntity, registeredClientConverter);
    }

    @Override
    public OAuth2Authorization findByToken(String token, OAuth2TokenType tokenType) {
        Map<String, Function<String, Optional<OAuth2AuthorizationEntity>>> tokenFinders = Map.of(
                "access_token", oAuth2AuthorizationRepository::findByAccessToken,
                "refresh_token", oAuth2AuthorizationRepository::findByRefreshToken,
                "code", oAuth2AuthorizationRepository::findByAuthorizationCode
        );

        Optional<OAuth2AuthorizationEntity> entityOpt;
        if (tokenType == null) {
            entityOpt = tokenFinders.values().stream()
                    .map(finder -> finder.apply(token))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .findFirst();
        } else {
            entityOpt = Optional.ofNullable(tokenFinders.get(tokenType.getValue()))
                    .orElseThrow(() -> new IllegalArgumentException("Unsupported token type: " + tokenType.getValue()))
                    .apply(token);
        }

        return entityOpt
                .map(entity -> {
                    RegisteredClientEntity registeredClientEntity = registeredClientRepository
                            .findByClientId(entity.getRegisteredClient().getClientId())
                            .orElseThrow(() -> new RuntimeException("RegisteredClient not found"));
                    return authorizationMapper.toAuthorization(entity, registeredClientEntity, registeredClientConverter);
                })
                .orElseThrow(() -> new RuntimeException("OAuth2Authorization not found for token: " + token));
    }

}

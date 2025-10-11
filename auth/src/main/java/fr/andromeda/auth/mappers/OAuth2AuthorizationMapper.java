package fr.andromeda.auth.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.andromeda.auth.entities.OAuth2AuthorizationEntity;
import fr.andromeda.auth.entities.OAuth2AuthorizationTokenEntity;
import fr.andromeda.auth.entities.RegisteredClientEntity;
import org.mapstruct.Mapper;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationCode;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {RegisteredClientMapper.class})
public abstract class OAuth2AuthorizationMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RegisteredClientConverter registeredClientConverter = new RegisteredClientConverter();

    public OAuth2Authorization toAuthorization(OAuth2AuthorizationEntity entity,
                                               RegisteredClientEntity registeredClientEntity,
                                               RegisteredClientConverter converter) {
        RegisteredClient registeredClient = converter.toSpringRegisteredClient(registeredClientEntity);

        OAuth2Authorization.Builder builder = OAuth2Authorization.withRegisteredClient(registeredClient)
                .id(entity.getId())
                .principalName(entity.getPrincipalName())
                .authorizationGrantType(new AuthorizationGrantType(entity.getAuthorizationGrantType()))
                .authorizedScopes(entity.getAuthorizedScopes());
        if (entity.getAttributes() != null) {
            try {
                Map<String, Object> attrs = objectMapper.readValue(entity.getAttributes(), Map.class);
                builder.attributes(a -> a.putAll(attrs));
            } catch (Exception e) {
                throw new RuntimeException("Erreur lors de la désérialisation des attributs OAuth2Authorization", e);
            }
        }
        for (OAuth2AuthorizationTokenEntity tokenEntity : entity.getTokens()) {
            switch (tokenEntity.getTokenType()) {
                case "access_token" -> builder.token(new OAuth2AccessToken(
                        OAuth2AccessToken.TokenType.BEARER,
                        tokenEntity.getTokenValue(),
                        tokenEntity.getIssuedAt(),
                        tokenEntity.getExpiresAt(),
                        tokenEntity.getScopes()
                ));
                case "refresh_token" -> builder.token(new OAuth2RefreshToken(
                        tokenEntity.getTokenValue(),
                        tokenEntity.getIssuedAt(),
                        tokenEntity.getExpiresAt()
                ));
                case "code" -> builder.token(new OAuth2AuthorizationCode(
                        tokenEntity.getTokenValue(),
                        tokenEntity.getIssuedAt(),
                        tokenEntity.getExpiresAt()
                ));
            }
        }
        return builder.build();
    }

    public OAuth2AuthorizationEntity toEntity(OAuth2Authorization authorization, RegisteredClientEntity registeredClientEntity) {
        OAuth2AuthorizationEntity entity = new OAuth2AuthorizationEntity();
        entity.setId(authorization.getId());
        entity.setPrincipalName(authorization.getPrincipalName());
        entity.setAuthorizationGrantType(authorization.getAuthorizationGrantType().getValue());
        entity.setAuthorizedScopes(authorization.getAuthorizedScopes());
        entity.setRegisteredClient(registeredClientEntity);
        try {
            entity.setAttributes(objectMapper.writeValueAsString(authorization.getAttributes()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erreur lors de la sérialisation des attributs OAuth2Authorization", e);
        }
        Set<OAuth2AuthorizationTokenEntity> tokenEntities = new HashSet<>();
        addTokenEntity(tokenEntities, entity, "access_token", authorization.getAccessToken());
        addTokenEntity(tokenEntities, entity, "refresh_token", authorization.getRefreshToken());
        addTokenEntity(tokenEntities, entity, "code", authorization.getToken(OAuth2AuthorizationCode.class));
        entity.setTokens(tokenEntities);
        return entity;
    }

    private <T extends AbstractOAuth2Token> void addTokenEntity(Set<OAuth2AuthorizationTokenEntity> tokenEntities,
                                                                OAuth2AuthorizationEntity parent,
                                                                String type,
                                                                OAuth2Authorization.Token<T> tokenWrapper) {
        if (tokenWrapper == null || tokenWrapper.getToken() == null) return;
        T token = tokenWrapper.getToken();
        OAuth2AuthorizationTokenEntity tokenEntity = new OAuth2AuthorizationTokenEntity();
        tokenEntity.setId(UUID.randomUUID().toString());
        tokenEntity.setAuthorization(parent);
        tokenEntity.setTokenType(type);
        tokenEntity.setTokenValue(token.getTokenValue());
        tokenEntity.setIssuedAt(token.getIssuedAt());
        tokenEntity.setExpiresAt(token.getExpiresAt());
        if (token instanceof OAuth2AccessToken at) {
            tokenEntity.setScopes(at.getScopes());
        }
        tokenEntities.add(tokenEntity);
    }
}
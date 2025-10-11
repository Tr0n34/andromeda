package fr.andromeda.auth.mappers;

import fr.andromeda.auth.entities.RegisteredClientEntity;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RegisteredClientConverter {

    public RegisteredClient toSpringRegisteredClient(RegisteredClientEntity entity) {
        return RegisteredClient.withId(entity.getId())
                .clientId(entity.getClientId())
                .clientSecret(entity.getClientSecret())
                .clientAuthenticationMethods(auth -> auth.addAll(
                        entity.getClientAuthenticationMethods().stream()
                                .map(org.springframework.security.oauth2.core.ClientAuthenticationMethod::new)
                                .collect(Collectors.toSet())
                ))
                .authorizationGrantTypes(grants -> grants.addAll(
                        entity.getAuthorizationGrantTypes().stream()
                                .map(org.springframework.security.oauth2.core.AuthorizationGrantType::new)
                                .collect(Collectors.toSet())
                ))
                .scopes(scopes -> scopes.addAll(entity.getScopes()))
                .redirectUris(uris -> uris.addAll(entity.getRedirectUris()))
                .clientSettings(ClientSettings.builder()
                        .requireProofKey(entity.getClientSettings().getRequireProofKey())
                        .requireAuthorizationConsent(entity.getClientSettings().getRequireAuthorizationConsent())
                        .build())
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(entity.getTokenSettings().getAccessTokenTimeToLive())
                        .refreshTokenTimeToLive(entity.getTokenSettings().getRefreshTokenTimeToLive())
                        .reuseRefreshTokens(entity.getTokenSettings().getReuseRefreshTokens())
                        .build())
                .build();
    }
}

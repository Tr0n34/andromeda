package fr.andromeda.auth.mappers;

import fr.andromeda.auth.entities.RegisteredClientEntity;
import org.mapstruct.Mapper;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = {
        RegisteredClient.class, org.springframework.security.oauth2.core.AuthorizationGrantType.class,
        org.springframework.security.oauth2.core.ClientAuthenticationMethod.class})
public interface RegisteredClientMapper {

    default RegisteredClient toRegisteredClient(RegisteredClientEntity entity) {
        return RegisteredClient.withId(entity.getId()).clientId(entity.getClientId())
                .clientSecret(entity.getClientSecret())
                .authorizationGrantTypes(grants -> grants.addAll(entity.getAuthorizationGrantTypes().stream()
                        .map(org.springframework.security.oauth2.core.AuthorizationGrantType::new)
                        .collect(Collectors.toSet()))).scopes(scopes -> scopes.addAll(entity.getScopes()))
                .clientAuthenticationMethods(methods -> methods.addAll(entity.getClientAuthenticationMethods().stream()
                        .map(org.springframework.security.oauth2.core.ClientAuthenticationMethod::new)
                        .collect(Collectors.toSet())))
                .redirectUris(uris -> uris.addAll(entity.getRedirectUris())).build();
    }

    default RegisteredClientEntity toEntity(RegisteredClient client) {
        RegisteredClientEntity entity = new RegisteredClientEntity();
        entity.setId(client.getId());
        entity.setClientId(client.getClientId());
        entity.setClientSecret(client.getClientSecret());
        entity.setAuthorizationGrantTypes(client.getAuthorizationGrantTypes().stream().map(org.springframework.security.oauth2.core.AuthorizationGrantType::getValue).collect(Collectors.toSet()));
        entity.setScopes(client.getScopes());
        entity.setClientAuthenticationMethods(client.getClientAuthenticationMethods().stream().map(org.springframework.security.oauth2.core.ClientAuthenticationMethod::getValue).collect(Collectors.toSet()));
        entity.setRedirectUris(client.getRedirectUris());
        return entity;
    }

}
package fr.andromeda.auth.mappers;

import fr.andromeda.auth.dto.authentication.ClientSettingsDTO;
import fr.andromeda.auth.dto.authentication.RegisteredClientDTO;
import fr.andromeda.auth.dto.authentication.TokenSettingsDTO;
import fr.andromeda.auth.entities.ClientSettingsEmbeddable;
import fr.andromeda.auth.entities.RegisteredClientEntity;
import fr.andromeda.auth.entities.TokenSettingsEmbeddable;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Mapper(componentModel = "spring", imports = {
        RegisteredClient.class, org.springframework.security.oauth2.core.AuthorizationGrantType.class,
        org.springframework.security.oauth2.core.ClientAuthenticationMethod.class})
public interface RegisteredClientMapper {

    default RegisteredClientDTO toDto(RegisteredClientEntity entity) {
        RegisteredClientDTO dto = new RegisteredClientDTO();
        dto.setId(entity.getId());
        dto.setClientId(entity.getClientId());
        dto.setClientSecret(entity.getClientSecret());
        dto.setScopes(entity.getScopes());
        dto.setAuthorizationGrantTypes(entity.getAuthorizationGrantTypes());
        dto.setClientAuthenticationMethods(entity.getClientAuthenticationMethods());
        dto.setRedirectUris(entity.getRedirectUris());
        Optional.ofNullable(entity.getClientSettings())
                .map(this::getClientSettingsDTO)
                .ifPresent(dto::setClientSettings);

        Optional.ofNullable(entity.getTokenSettings())
                .map(this::getTokenSettingsDTO)
                .ifPresent(dto::setTokenSettings);
        return dto;
    }

    default RegisteredClientEntity toEntity(RegisteredClientDTO dto) {
        return Optional.ofNullable(dto)
                .map(d -> {
                    RegisteredClientEntity entity = getRegisteredClientEntity(d);
                    Optional.ofNullable(d.getClientSettings())
                            .map(this::mapClientSettingsEntity)
                            .ifPresent(entity::setClientSettings);
                    Optional.ofNullable(d.getTokenSettings())
                            .map(this::mapTokenSettingsEntity)
                            .ifPresent(entity::setTokenSettings);
                    return entity;
                })
                .orElse(null);
    }

    private static RegisteredClientEntity getRegisteredClientEntity(RegisteredClientDTO d) {
        RegisteredClientEntity entity = new RegisteredClientEntity();
        entity.setId(d.getId());
        entity.setClientId(d.getClientId());
        entity.setClientSecret(d.getClientSecret());
        entity.setClientName(d.getClientName());
        entity.setScopes(d.getScopes());
        // entity.setPostLogoutRedirectUris(d.getPostLogoutRedirectUris());
        entity.setAuthorizationGrantTypes(d.getAuthorizationGrantTypes());
        entity.setClientAuthenticationMethods(d.getClientAuthenticationMethods());
        entity.setRedirectUris(d.getRedirectUris());
        return entity;
    }


    private ClientSettingsDTO getClientSettingsDTO(ClientSettingsEmbeddable cs) {
        ClientSettingsDTO dto = new ClientSettingsDTO();
        dto.setRequireProofKey(cs.getRequireProofKey());
        dto.setRequireAuthorizationConsent(cs.getRequireAuthorizationConsent());
        dto.setJwkSetUrl(cs.getJwkSetUrl());
        dto.setTokenEndpointAuthenticationSigningAlgorithm(cs.getTokenEndpointAuthenticationSigningAlgorithm());
        dto.setX509CertificateSubjectDN(cs.getX509CertificateSubjectDN());
        return dto;
    }

    private TokenSettingsDTO getTokenSettingsDTO(TokenSettingsEmbeddable ts) {
        TokenSettingsDTO dto = new TokenSettingsDTO();
        dto.setAccessTokenTimeToLive(ts.getAccessTokenTimeToLive());
        dto.setRefreshTokenTimeToLive(ts.getRefreshTokenTimeToLive());
        dto.setReuseRefreshTokens(ts.getReuseRefreshTokens());
        dto.setAccessTokenFormat(ts.getAccessTokenFormat());
        dto.setIdTokenSignatureAlgorithm(ts.getIdTokenSignatureAlgorithm());
        return dto;
    }

    private ClientSettingsEmbeddable mapClientSettingsEntity(ClientSettingsDTO dto) {
        Map<String, Object> settings = new HashMap<>();
        settings.put("requireProofKey", dto.getRequireProofKey());
        settings.put("requireAuthorizationConsent", dto.getRequireAuthorizationConsent());
        settings.put("jwkSetUrl", dto.getJwkSetUrl());
        settings.put("tokenEndpointAuthenticationSigningAlgorithm", dto.getTokenEndpointAuthenticationSigningAlgorithm());
        settings.put("x509CertificateSubjectDN", dto.getX509CertificateSubjectDN());
        return new ClientSettingsEmbeddable(settings);
    }

    private TokenSettingsEmbeddable mapTokenSettingsEntity(TokenSettingsDTO dto) {
        Map<String, Object> settings = new HashMap<>();
        if (dto.getAccessTokenTimeToLive() != null)
            settings.put("accessTokenTimeToLive", dto.getAccessTokenTimeToLive());
        if (dto.getRefreshTokenTimeToLive() != null)
            settings.put("refreshTokenTimeToLive", dto.getRefreshTokenTimeToLive());
        if (dto.getReuseRefreshTokens() != null)
            settings.put("reuseRefreshTokens", dto.getReuseRefreshTokens());
        if (dto.getAccessTokenFormat() != null)
            settings.put("accessTokenFormat", dto.getAccessTokenFormat());
        if (dto.getIdTokenSignatureAlgorithm() != null)
            settings.put("idTokenSignatureAlgorithm", dto.getIdTokenSignatureAlgorithm());
        return new TokenSettingsEmbeddable(settings);
    }


    private static Map<String, Object> getSettingsMap(RegisteredClientDTO dto) {
        ClientSettingsDTO csDto = dto.getClientSettings();
        Map<String, Object> settingsMap = new HashMap<>();
        settingsMap.put("requireProofKey", csDto.getRequireProofKey());
        settingsMap.put("requireAuthorizationConsent", csDto.getRequireAuthorizationConsent());
        settingsMap.put("jwkSetUrl", csDto.getJwkSetUrl());
        settingsMap.put("tokenEndpointAuthenticationSigningAlgorithm", csDto.getTokenEndpointAuthenticationSigningAlgorithm());
        settingsMap.put("x509CertificateSubjectDN", csDto.getX509CertificateSubjectDN());
        return settingsMap;
    }

}
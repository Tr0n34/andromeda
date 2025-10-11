package fr.andromeda.auth.dto.authentication;

import fr.andromeda.auth.entities.ClientSettingsEmbeddable;
import fr.andromeda.auth.entities.TokenSettingsEmbeddable;

import java.util.Set;

public class RegisteredClientDTO  {

    private String id;
    private String clientId;
    private String clientSecret;
    private String clientName;
    private Set<String> scopes;
    private Set<String> authorizationGrantTypes;
    private Set<String> clientAuthenticationMethods;
    private Set<String> redirectUris;

    private ClientSettingsDTO clientSettings;
    private TokenSettingsDTO tokenSettings;

    public String getId() {
        return id;
    }

    public RegisteredClientDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    public RegisteredClientDTO setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public RegisteredClientDTO setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

    public Set<String> getScopes() {
        return scopes;
    }

    public RegisteredClientDTO setScopes(Set<String> scopes) {
        this.scopes = scopes;
        return this;
    }

    public Set<String> getAuthorizationGrantTypes() {
        return authorizationGrantTypes;
    }

    public RegisteredClientDTO setAuthorizationGrantTypes(Set<String> authorizationGrantTypes) {
        this.authorizationGrantTypes = authorizationGrantTypes;
        return this;
    }

    public Set<String> getClientAuthenticationMethods() {
        return clientAuthenticationMethods;
    }

    public RegisteredClientDTO setClientAuthenticationMethods(Set<String> clientAuthenticationMethods) {
        this.clientAuthenticationMethods = clientAuthenticationMethods;
        return this;
    }

    public Set<String> getRedirectUris() {
        return redirectUris;
    }

    public RegisteredClientDTO setRedirectUris(Set<String> redirectUris) {
        this.redirectUris = redirectUris;
        return this;
    }

    public ClientSettingsDTO getClientSettings() {
        return clientSettings;
    }

    public RegisteredClientDTO setClientSettings(ClientSettingsDTO clientSettings) {
        this.clientSettings = clientSettings;
        return this;
    }

    public TokenSettingsDTO getTokenSettings() {
        return tokenSettings;
    }

    public RegisteredClientDTO setTokenSettings(TokenSettingsDTO tokenSettings) {
        this.tokenSettings = tokenSettings;
        return this;
    }

    public String getClientName() {
        return clientName;
    }

    public RegisteredClientDTO setClientName(String clientName) {
        this.clientName = clientName;
        return this;
    }

}

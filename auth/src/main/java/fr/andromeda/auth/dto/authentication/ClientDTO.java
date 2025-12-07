package fr.andromeda.auth.dto.authentication;

import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Instant;
import java.util.Set;

public class ClientDTO {

    private String clientId;
    private Instant clientIdIssuedAt;
    private String clientSecret;
    private Instant clientSecretExpiresAt;
    private String clientName;
    private Set<ClientAuthenticationMethod> clientAuthenticationMethods;
    private Set<AuthorizationGrantType> authorizationGrantTypes;
    private Set<String> redirectUris;
    private Set<String> postLogoutRedirectUris;
    private Set<String> scopes;
    private ClientSettings clientSettings;
    private TokenSettings tokenSettings;

    public String getClientId() {
        return clientId;
    }

    public ClientDTO setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public Instant getClientIdIssuedAt() {
        return clientIdIssuedAt;
    }

    public ClientDTO setClientIdIssuedAt(Instant clientIdIssuedAt) {
        this.clientIdIssuedAt = clientIdIssuedAt;
        return this;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public ClientDTO setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

    public Instant getClientSecretExpiresAt() {
        return clientSecretExpiresAt;
    }

    public ClientDTO setClientSecretExpiresAt(Instant clientSecretExpiresAt) {
        this.clientSecretExpiresAt = clientSecretExpiresAt;
        return this;
    }

    public String getClientName() {
        return clientName;
    }

    public ClientDTO setClientName(String clientName) {
        this.clientName = clientName;
        return this;
    }

    public Set<ClientAuthenticationMethod> getClientAuthenticationMethods() {
        return clientAuthenticationMethods;
    }

    public ClientDTO setClientAuthenticationMethods(Set<ClientAuthenticationMethod> clientAuthenticationMethods) {
        this.clientAuthenticationMethods = clientAuthenticationMethods;
        return this;
    }

    public Set<AuthorizationGrantType> getAuthorizationGrantTypes() {
        return authorizationGrantTypes;
    }

    public ClientDTO setAuthorizationGrantTypes(Set<AuthorizationGrantType> authorizationGrantTypes) {
        this.authorizationGrantTypes = authorizationGrantTypes;
        return this;
    }

    public Set<String> getRedirectUris() {
        return redirectUris;
    }

    public ClientDTO setRedirectUris(Set<String> redirectUris) {
        this.redirectUris = redirectUris;
        return this;
    }

    public Set<String> getPostLogoutRedirectUris() {
        return postLogoutRedirectUris;
    }

    public ClientDTO setPostLogoutRedirectUris(Set<String> postLogoutRedirectUris) {
        this.postLogoutRedirectUris = postLogoutRedirectUris;
        return this;
    }

    public Set<String> getScopes() {
        return scopes;
    }

    public ClientDTO setScopes(Set<String> scopes) {
        this.scopes = scopes;
        return this;
    }

    public ClientSettings getClientSettings() {
        return clientSettings;
    }

    public ClientDTO setClientSettings(ClientSettings clientSettings) {
        this.clientSettings = clientSettings;
        return this;
    }

    public TokenSettings getTokenSettings() {
        return tokenSettings;
    }

    public ClientDTO setTokenSettings(TokenSettings tokenSettings) {
        this.tokenSettings = tokenSettings;
        return this;
    }

}

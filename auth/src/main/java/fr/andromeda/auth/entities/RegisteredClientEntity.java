package fr.andromeda.auth.entities;

import fr.andromeda.api.entities.AuditableEntity;
import fr.andromeda.api.entities.IEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "oauth2_registered_client")
public class RegisteredClientEntity {

    @Id
    private String id;

    @Column(nullable = false, unique = true)
    private String clientId;

    @Column(nullable = false)
    private String clientSecret;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "registered_client_scopes", joinColumns = @JoinColumn(name = "client_id"))
    @Column(name = "scope")
    private Set<String> scopes = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "registered_client_grant_types", joinColumns = @JoinColumn(name = "client_id"))
    @Column(name = "grant_type")
    private Set<String> authorizationGrantTypes = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "registered_client_auth_methods", joinColumns = @JoinColumn(name = "client_id"))
    @Column(name = "auth_method")
    private Set<String> clientAuthenticationMethods = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "registered_client_redirect_uris", joinColumns = @JoinColumn(name = "client_id"))
    @Column(name = "redirect_uri")
    private Set<String> redirectUris = new HashSet<>();

    public String getId() {
        return id;
    }

    public RegisteredClientEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    public RegisteredClientEntity setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public RegisteredClientEntity setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

    public Set<String> getScopes() {
        return scopes;
    }

    public RegisteredClientEntity setScopes(Set<String> scopes) {
        this.scopes = scopes;
        return this;
    }

    public Set<String> getAuthorizationGrantTypes() {
        return authorizationGrantTypes;
    }

    public RegisteredClientEntity setAuthorizationGrantTypes(Set<String> authorizationGrantTypes) {
        this.authorizationGrantTypes = authorizationGrantTypes;
        return this;
    }

    public Set<String> getClientAuthenticationMethods() {
        return clientAuthenticationMethods;
    }

    public RegisteredClientEntity setClientAuthenticationMethods(Set<String> clientAuthenticationMethods) {
        this.clientAuthenticationMethods = clientAuthenticationMethods;
        return this;
    }

    public Set<String> getRedirectUris() {
        return redirectUris;
    }

    public RegisteredClientEntity setRedirectUris(Set<String> redirectUris) {
        this.redirectUris = redirectUris;
        return this;
    }

}


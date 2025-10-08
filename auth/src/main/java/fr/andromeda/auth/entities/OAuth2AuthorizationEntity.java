package fr.andromeda.auth.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "oauth2_authorization")
public class OAuth2AuthorizationEntity {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "registered_client_id", nullable = false)
    private RegisteredClientEntity registeredClient;

    @Column(nullable = false)
    private String principalName;

    @Column(nullable = false)
    private String authorizationGrantType;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "oauth2_authorization_scopes", joinColumns = @JoinColumn(name = "authorization_id"))
    @Column(name = "scope")
    private Set<String> authorizedScopes = new HashSet<>();

    @OneToMany(mappedBy = "authorization", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<OAuth2AuthorizationTokenEntity> tokens = new HashSet<>();

    @Lob
    @Column(columnDefinition = "TEXT")
    private String attributes; // JSON map

    public String getId() {
        return id;
    }

    public OAuth2AuthorizationEntity setId(String id) {
        this.id = id;
        return this;
    }

    public RegisteredClientEntity getRegisteredClient() {
        return registeredClient;
    }

    public OAuth2AuthorizationEntity setRegisteredClient(RegisteredClientEntity registeredClient) {
        this.registeredClient = registeredClient;
        return this;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public OAuth2AuthorizationEntity setPrincipalName(String principalName) {
        this.principalName = principalName;
        return this;
    }

    public String getAuthorizationGrantType() {
        return authorizationGrantType;
    }

    public OAuth2AuthorizationEntity setAuthorizationGrantType(String authorizationGrantType) {
        this.authorizationGrantType = authorizationGrantType;
        return this;
    }

    public Set<String> getAuthorizedScopes() {
        return authorizedScopes;
    }

    public OAuth2AuthorizationEntity setAuthorizedScopes(Set<String> authorizedScopes) {
        this.authorizedScopes = authorizedScopes;
        return this;
    }

    public Set<OAuth2AuthorizationTokenEntity> getTokens() {
        return tokens;
    }

    public OAuth2AuthorizationEntity setTokens(Set<OAuth2AuthorizationTokenEntity> tokens) {
        this.tokens = tokens;
        return this;
    }

    public String getAttributes() {
        return attributes;
    }

    public OAuth2AuthorizationEntity setAttributes(String attributes) {
        this.attributes = attributes;
        return this;
    }
}
package fr.andromeda.auth.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "oauth2_authorization_token")
public class OAuth2AuthorizationTokenEntity {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorization_id", nullable = false)
    private OAuth2AuthorizationEntity authorization;

    @Column(nullable = false)
    private String tokenType; // access_token, refresh_token, id_token

    @Column(nullable = false)
    private String tokenValue;

    private Instant issuedAt;
    private Instant expiresAt;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "oauth2_authorization_token_scopes", joinColumns = @JoinColumn(name = "token_id"))
    @Column(name = "scope")
    private Set<String> scopes = new HashSet<>();

    public String getId() {
        return id;
    }

    public OAuth2AuthorizationTokenEntity setId(String id) {
        this.id = id;
        return this;
    }

    public OAuth2AuthorizationEntity getAuthorization() {
        return authorization;
    }

    public OAuth2AuthorizationTokenEntity setAuthorization(OAuth2AuthorizationEntity authorization) {
        this.authorization = authorization;
        return this;
    }

    public String getTokenType() {
        return tokenType;
    }

    public OAuth2AuthorizationTokenEntity setTokenType(String tokenType) {
        this.tokenType = tokenType;
        return this;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public OAuth2AuthorizationTokenEntity setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
        return this;
    }

    public Instant getIssuedAt() {
        return issuedAt;
    }

    public OAuth2AuthorizationTokenEntity setIssuedAt(Instant issuedAt) {
        this.issuedAt = issuedAt;
        return this;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public OAuth2AuthorizationTokenEntity setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }

    public Set<String> getScopes() {
        return scopes;
    }

    public OAuth2AuthorizationTokenEntity setScopes(Set<String> scopes) {
        this.scopes = scopes;
        return this;
    }

}
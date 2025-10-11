package fr.andromeda.auth.entities;

import jakarta.persistence.Embeddable;
import org.springframework.security.oauth2.server.authorization.settings.AbstractSettings;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Embeddable
public class TokenSettingsEmbeddable extends AbstractSettings {

    private Duration accessTokenTimeToLive = Duration.ofHours(1);
    private Duration refreshTokenTimeToLive = Duration.ofDays(30);
    private Duration authorizationCodeTimeToLive = Duration.ofMinutes(5);
    private Duration deviceCodeTimeToLive = Duration.ofMinutes(5);
    private Boolean reuseRefreshTokens = true;
    private Boolean x509CertificateBoundAccessTokens = false;
    private String idTokenSignatureAlgorithm = "RS256";
    private String accessTokenFormat = "self-contained";

    public TokenSettingsEmbeddable() {
        super(HashMap.newHashMap(10));
    }

    public TokenSettingsEmbeddable(Map<String, Object> settings) {
        super(settings);
    }

    public Duration getAccessTokenTimeToLive() {
        return accessTokenTimeToLive;
    }

    public TokenSettingsEmbeddable setAccessTokenTimeToLive(Duration accessTokenTimeToLive) {
        this.accessTokenTimeToLive = accessTokenTimeToLive;
        return this;
    }

    public Duration getRefreshTokenTimeToLive() {
        return refreshTokenTimeToLive;
    }

    public TokenSettingsEmbeddable setRefreshTokenTimeToLive(Duration refreshTokenTimeToLive) {
        this.refreshTokenTimeToLive = refreshTokenTimeToLive;
        return this;
    }

    public Duration getAuthorizationCodeTimeToLive() {
        return authorizationCodeTimeToLive;
    }

    public TokenSettingsEmbeddable setAuthorizationCodeTimeToLive(Duration authorizationCodeTimeToLive) {
        this.authorizationCodeTimeToLive = authorizationCodeTimeToLive;
        return this;
    }

    public Duration getDeviceCodeTimeToLive() {
        return deviceCodeTimeToLive;
    }

    public TokenSettingsEmbeddable setDeviceCodeTimeToLive(Duration deviceCodeTimeToLive) {
        this.deviceCodeTimeToLive = deviceCodeTimeToLive;
        return this;
    }

    public Boolean getReuseRefreshTokens() {
        return reuseRefreshTokens;
    }

    public TokenSettingsEmbeddable setReuseRefreshTokens(Boolean reuseRefreshTokens) {
        this.reuseRefreshTokens = reuseRefreshTokens;
        return this;
    }

    public Boolean getX509CertificateBoundAccessTokens() {
        return x509CertificateBoundAccessTokens;
    }

    public TokenSettingsEmbeddable setX509CertificateBoundAccessTokens(Boolean x509CertificateBoundAccessTokens) {
        this.x509CertificateBoundAccessTokens = x509CertificateBoundAccessTokens;
        return this;
    }

    public String getIdTokenSignatureAlgorithm() {
        return idTokenSignatureAlgorithm;
    }

    public TokenSettingsEmbeddable setIdTokenSignatureAlgorithm(String idTokenSignatureAlgorithm) {
        this.idTokenSignatureAlgorithm = idTokenSignatureAlgorithm;
        return this;
    }

    public String getAccessTokenFormat() {
        return accessTokenFormat;
    }

    public TokenSettingsEmbeddable setAccessTokenFormat(String accessTokenFormat) {
        this.accessTokenFormat = accessTokenFormat;
        return this;
    }

    public Boolean isReuseRefreshTokens() {
        return reuseRefreshTokens;
    }

    public Boolean isX509CertificateBoundAccessTokens() {
        return x509CertificateBoundAccessTokens;
    }

}

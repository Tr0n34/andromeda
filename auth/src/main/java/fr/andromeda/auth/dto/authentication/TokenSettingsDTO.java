package fr.andromeda.auth.dto.authentication;

import java.time.Duration;

public class TokenSettingsDTO {

    private Duration accessTokenTimeToLive;
    private Duration refreshTokenTimeToLive;
    private Boolean reuseRefreshTokens;
    private String accessTokenFormat;
    private String idTokenSignatureAlgorithm;

    public Duration getAccessTokenTimeToLive() {
        return accessTokenTimeToLive;
    }

    public TokenSettingsDTO setAccessTokenTimeToLive(Duration accessTokenTimeToLive) {
        this.accessTokenTimeToLive = accessTokenTimeToLive;
        return this;
    }

    public Duration getRefreshTokenTimeToLive() {
        return refreshTokenTimeToLive;
    }

    public TokenSettingsDTO setRefreshTokenTimeToLive(Duration refreshTokenTimeToLive) {
        this.refreshTokenTimeToLive = refreshTokenTimeToLive;
        return this;
    }

    public Boolean getReuseRefreshTokens() {
        return reuseRefreshTokens;
    }

    public TokenSettingsDTO setReuseRefreshTokens(Boolean reuseRefreshTokens) {
        this.reuseRefreshTokens = reuseRefreshTokens;
        return this;
    }

    public String getAccessTokenFormat() {
        return accessTokenFormat;
    }

    public TokenSettingsDTO setAccessTokenFormat(String accessTokenFormat) {
        this.accessTokenFormat = accessTokenFormat;
        return this;
    }

    public String getIdTokenSignatureAlgorithm() {
        return idTokenSignatureAlgorithm;
    }

    public TokenSettingsDTO setIdTokenSignatureAlgorithm(String idTokenSignatureAlgorithm) {
        this.idTokenSignatureAlgorithm = idTokenSignatureAlgorithm;
        return this;
    }

}

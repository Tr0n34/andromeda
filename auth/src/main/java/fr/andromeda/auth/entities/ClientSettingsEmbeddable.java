package fr.andromeda.auth.entities;

import jakarta.persistence.Embeddable;
import org.springframework.security.oauth2.server.authorization.settings.AbstractSettings;

import java.util.HashMap;
import java.util.Map;

@Embeddable
public class ClientSettingsEmbeddable extends AbstractSettings {

    private Boolean requireProofKey = false;
    private Boolean requireAuthorizationConsent = false;
    private String jwkSetUrl;
    private String tokenEndpointAuthenticationSigningAlgorithm;
    private String x509CertificateSubjectDN;

    public ClientSettingsEmbeddable() {
        super(HashMap.newHashMap(5));
        super.getSettings().put("dummy", "dummy");
    }

    public ClientSettingsEmbeddable(Map<String, Object> settings) {
        super(settings);
    }

    public Boolean getRequireProofKey() {
        return requireProofKey;
    }

    public ClientSettingsEmbeddable setRequireProofKey(Boolean requireProofKey) {
        this.requireProofKey = requireProofKey;
        return this;
    }

    public Boolean getRequireAuthorizationConsent() {
        return requireAuthorizationConsent;
    }

    public ClientSettingsEmbeddable setRequireAuthorizationConsent(Boolean requireAuthorizationConsent) {
        this.requireAuthorizationConsent = requireAuthorizationConsent;
        return this;
    }

    public String getJwkSetUrl() {
        return jwkSetUrl;
    }

    public ClientSettingsEmbeddable setJwkSetUrl(String jwkSetUrl) {
        this.jwkSetUrl = jwkSetUrl;
        return this;
    }

    public String getTokenEndpointAuthenticationSigningAlgorithm() {
        return tokenEndpointAuthenticationSigningAlgorithm;
    }

    public ClientSettingsEmbeddable setTokenEndpointAuthenticationSigningAlgorithm(String tokenEndpointAuthenticationSigningAlgorithm) {
        this.tokenEndpointAuthenticationSigningAlgorithm = tokenEndpointAuthenticationSigningAlgorithm;
        return this;
    }

    public String getX509CertificateSubjectDN() {
        return x509CertificateSubjectDN;
    }

    public ClientSettingsEmbeddable setX509CertificateSubjectDN(String x509CertificateSubjectDN) {
        this.x509CertificateSubjectDN = x509CertificateSubjectDN;
        return this;
    }

    public Boolean isRequireProofKey() {
        return requireProofKey;
    }

    public Boolean isRequireAuthorizationConsent() {
        return requireAuthorizationConsent;
    }
}

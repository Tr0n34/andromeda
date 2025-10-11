package fr.andromeda.auth.dto.authentication;

public class ClientSettingsDTO {

    private Boolean requireProofKey;
    private Boolean requireAuthorizationConsent;
    private String jwkSetUrl;
    private String tokenEndpointAuthenticationSigningAlgorithm;
    private String x509CertificateSubjectDN;

    public Boolean getRequireProofKey() {
        return requireProofKey;
    }

    public ClientSettingsDTO setRequireProofKey(Boolean requireProofKey) {
        this.requireProofKey = requireProofKey;
        return this;
    }

    public Boolean getRequireAuthorizationConsent() {
        return requireAuthorizationConsent;
    }

    public ClientSettingsDTO setRequireAuthorizationConsent(Boolean requireAuthorizationConsent) {
        this.requireAuthorizationConsent = requireAuthorizationConsent;
        return this;
    }

    public String getJwkSetUrl() {
        return jwkSetUrl;
    }

    public ClientSettingsDTO setJwkSetUrl(String jwkSetUrl) {
        this.jwkSetUrl = jwkSetUrl;
        return this;
    }

    public String getTokenEndpointAuthenticationSigningAlgorithm() {
        return tokenEndpointAuthenticationSigningAlgorithm;
    }

    public ClientSettingsDTO setTokenEndpointAuthenticationSigningAlgorithm(String tokenEndpointAuthenticationSigningAlgorithm) {
        this.tokenEndpointAuthenticationSigningAlgorithm = tokenEndpointAuthenticationSigningAlgorithm;
        return this;
    }

    public String getX509CertificateSubjectDN() {
        return x509CertificateSubjectDN;
    }

    public ClientSettingsDTO setX509CertificateSubjectDN(String x509CertificateSubjectDN) {
        this.x509CertificateSubjectDN = x509CertificateSubjectDN;
        return this;
    }

}

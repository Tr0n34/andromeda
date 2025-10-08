package fr.andromeda.auth.configurations.security;

public class PasswordExpiration {

    private PasswordExpirationPolicy passwordExpirationPolicy;
    private Long passwordExpirationDuration;

    public PasswordExpiration(PasswordExpirationPolicy passwordExpirationPolicy, Long passwordExpirationDuration) {
        this.passwordExpirationPolicy = passwordExpirationPolicy;
        this.passwordExpirationDuration = passwordExpirationDuration;
    }

    public PasswordExpirationPolicy getPasswordExpirationPolicy() {
        return passwordExpirationPolicy;
    }

    public PasswordExpiration setPasswordExpirationPolicy(PasswordExpirationPolicy passwordExpirationPolicy) {
        this.passwordExpirationPolicy = passwordExpirationPolicy;
        return this;
    }

    public Long getPasswordExpirationDuration() {
        return passwordExpirationDuration;
    }

    public PasswordExpiration setPasswordExpirationDuration(Long passwordExpirationDuration) {
        this.passwordExpirationDuration = passwordExpirationDuration;
        return this;
    }

}

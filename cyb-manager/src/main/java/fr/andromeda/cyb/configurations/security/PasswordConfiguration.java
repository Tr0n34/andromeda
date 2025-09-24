package fr.andromeda.cyb.configurations.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Duration;

import static java.time.Duration.*;

@Configuration
public class PasswordConfiguration {

    @Value("${account.password.expiration.policy}")
    private PasswordExpirationPolicy passwordExpirationPolicy;

    @Value("${account.password.expiration.duration:INFINITE}")
    private Long passwordExpirationDuration;

    @Bean
    public PasswordExpiration  passwordExpiration() {
        return new PasswordExpiration(passwordExpirationPolicy, passwordExpirationDuration);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public PasswordExpirationPolicy getPasswordExpirationPolicy() {
        return passwordExpirationPolicy;
    }
    
    public Duration getPasswordDuration() {
        return switch (passwordExpirationPolicy) {
            case DAYS -> ofDays(this.passwordExpirationDuration);
            case HOURS -> ofHours(this.passwordExpirationDuration);
            case MINUTES -> ofMinutes(this.passwordExpirationDuration);
            case SECONDS -> ofSeconds(this.passwordExpirationDuration);
            case MONTH ->  ofDays(this.passwordExpirationDuration * 30);
            case YEARS -> ofDays(this.passwordExpirationDuration * 365);
            default -> null;
        };
    }

    public Long getPasswordExpirationDuration() {
        return passwordExpirationDuration;
    }

    public PasswordConfiguration setPasswordExpirationDuration(Long passwordExpirationDuration) {
        this.passwordExpirationDuration = passwordExpirationDuration;
        return this;
    }

    public PasswordConfiguration setPasswordExpirationPolicy(PasswordExpirationPolicy passwordExpirationPolicy) {
        this.passwordExpirationPolicy = passwordExpirationPolicy;
        return this;
    }

}

package fr.andromeda.auth.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.jwt.keystore")
public class JwtKeyStoreProperties {

    private String location;
    private String password;
    private String keyAlias;
    private String keyPassword;

    public String getLocation() {
        return location;
    }

    public JwtKeyStoreProperties setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public JwtKeyStoreProperties setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getKeyAlias() {
        return keyAlias;
    }

    public JwtKeyStoreProperties setKeyAlias(String keyAlias) {
        this.keyAlias = keyAlias;
        return this;
    }

    public String getKeyPassword() {
        return keyPassword;
    }

    public JwtKeyStoreProperties setKeyPassword(String keyPassword) {
        this.keyPassword = keyPassword;
        return this;
    }

}

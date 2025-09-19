package fr.andromeda.cyb.dto.authentification;

import fr.andromeda.cyb.dto.interfaces.IDTO;
import fr.andromeda.cyb.dto.UserDTO;

import java.time.Instant;

public class RefreshTokenDTO implements IDTO {

    private Long id;
    private UserDTO user;
    private String token;
    private Instant issuedAt;
    private Instant expiresAt;
    private boolean revoked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public RefreshTokenDTO setUser(UserDTO user) {
        this.user = user;
        return this;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Instant issuedAt) {
        this.issuedAt = issuedAt;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }
}

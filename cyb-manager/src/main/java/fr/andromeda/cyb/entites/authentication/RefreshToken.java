package fr.andromeda.cyb.entites.authentication;

import fr.andromeda.cyb.entites.IEntity;
import fr.andromeda.cyb.entites.User;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "refresh_tokens")
public class RefreshToken implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(nullable = false, length = 255)
    private String tokenHash;
    @Column(nullable = false)
    private Instant issuedAt;
    @Column(nullable = false)
    private Instant expiresAt;
    @Column(nullable = false)
    private boolean revoked;

    public Long getId() {
        return id;
    }

    public RefreshToken setId(Long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public RefreshToken setUser(User user) {
        this.user = user;
        return this;
    }

    public String getTokenHash() {
        return tokenHash;
    }

    public RefreshToken setTokenHash(String tokenHash) {
        this.tokenHash = tokenHash;
        return this;
    }

    public Instant getIssuedAt() {
        return issuedAt;
    }

    public RefreshToken setIssuedAt(Instant issuedAt) {
        this.issuedAt = issuedAt;
        return this;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public RefreshToken setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public RefreshToken setRevoked(boolean revoked) {
        this.revoked = revoked;
        return this;
    }

}

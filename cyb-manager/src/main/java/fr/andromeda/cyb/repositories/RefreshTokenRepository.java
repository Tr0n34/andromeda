package fr.andromeda.cyb.repositories;

import fr.andromeda.cyb.entites.User;
import fr.andromeda.cyb.entites.authentication.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByTokenHashAndRevokedFalse(String tokenHash);

    Optional<RefreshToken> findByUserAndRevokedFalseAndExpiresAtAfter(User user, Instant now);

    Optional<RefreshToken> findByIdAndRevokedFalseAndExpiresAtAfter(Long id, Instant now);

    void deleteByRevokedTrue();

    void deleteByUserId(Long userId);

}

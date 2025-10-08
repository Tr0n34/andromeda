package fr.andromeda.auth.repositories;

import fr.andromeda.auth.entities.OAuth2AuthorizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OAuth2AuthorizationRepository extends JpaRepository<OAuth2AuthorizationEntity, String> {

    @Query("""
        SELECT a
        FROM OAuth2AuthorizationEntity a
        JOIN a.tokens t
        WHERE t.tokenType = 'access_token' AND t.tokenValue = :token
    """)
    Optional<OAuth2AuthorizationEntity> findByAccessToken(@Param("token") String token);

    @Query("""
        SELECT a
        FROM OAuth2AuthorizationEntity a
        JOIN a.tokens t
        WHERE t.tokenType = 'refresh_token' AND t.tokenValue = :token
    """)
    Optional<OAuth2AuthorizationEntity> findByRefreshToken(@Param("token") String token);

    @Query("""
        SELECT a
        FROM OAuth2AuthorizationEntity a
        JOIN a.tokens t
        WHERE t.tokenType = 'code' AND t.tokenValue = :code
    """)
    Optional<OAuth2AuthorizationEntity> findByAuthorizationCode(@Param("code") String code);

}

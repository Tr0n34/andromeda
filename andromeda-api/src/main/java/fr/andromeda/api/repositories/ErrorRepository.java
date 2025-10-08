package fr.andromeda.api.repositories;

import fr.andromeda.api.entities.errors.Error;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ErrorRepository extends JpaRepository<Error, Long> {

    Optional<Error>  findByCode(String code);

    Optional<Error> findByStatus(HttpStatus status);

    List<Error> findAllByStatus(HttpStatus status);

    Optional<List<Error>> findAllByStatusAndEntityName(HttpStatus status, String entityName);

}

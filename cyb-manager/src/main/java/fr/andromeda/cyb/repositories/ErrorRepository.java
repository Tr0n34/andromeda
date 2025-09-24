package fr.andromeda.cyb.repositories;

import fr.andromeda.cyb.entites.errors.Error;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public interface ErrorRepository extends JpaRepository<Error, Long> {

    Optional<Error>  findByCode(String code);

    Optional<Error> findByStatus(HttpStatus status);

}

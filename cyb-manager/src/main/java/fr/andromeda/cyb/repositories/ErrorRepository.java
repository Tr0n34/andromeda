package fr.andromeda.cyb.repositories;

import fr.andromeda.cyb.entites.errors.Error;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

public interface ErrorRepository extends JpaRepository<Error, Long> {

    Error findByCode(String code);

    Error findByStatus(HttpStatus status);

}

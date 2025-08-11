package fr.andromeda.cyb.repositories;

import fr.andromeda.cyb.entites.ErrorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

public interface ErrorRepository extends JpaRepository<ErrorEntity, Long> {

    ErrorEntity findByCode(String code);

    ErrorEntity findByStatus(HttpStatus status);

}

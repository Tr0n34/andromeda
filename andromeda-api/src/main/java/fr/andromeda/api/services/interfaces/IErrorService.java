package fr.andromeda.api.services.interfaces;

import fr.andromeda.api.dto.errors.ErrorDTO;
import fr.andromeda.api.entities.errors.Error;
import fr.andromeda.api.exceptions.ResourceNotFoundException;
import fr.andromeda.api.repositories.ErrorRepository;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface IErrorService extends ICrudService<ErrorDTO, Error, ErrorRepository, Long> {

    ErrorDTO findByCode(String code) throws ResourceNotFoundException;

    ErrorDTO findByHttpStatus(HttpStatus status);

    List<ErrorDTO> findAllByStatusAndEntityName(HttpStatus status, String entityName) throws ResourceNotFoundException;

    void createAll(List<ErrorDTO> errors);

}

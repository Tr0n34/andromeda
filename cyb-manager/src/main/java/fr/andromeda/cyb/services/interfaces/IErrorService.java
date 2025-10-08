package fr.andromeda.cyb.services.interfaces;

import fr.andromeda.api.exceptions.ResourceNotFoundException;
import fr.andromeda.cyb.dto.errors.ErrorDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface IErrorService {

    ErrorDTO findByCode(String code) throws ResourceNotFoundException;

    ErrorDTO findByHttpStatus(HttpStatus status);

    List<ErrorDTO> findAllByStatusAndEntityName(HttpStatus status, String entityName) throws ResourceNotFoundException;

}

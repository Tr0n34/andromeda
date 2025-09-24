package fr.andromeda.cyb.services.interfaces;

import fr.andromeda.cyb.dto.errors.ErrorDTO;
import fr.andromeda.cyb.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface IErrorService {

    ErrorDTO findByCode(String code) throws ResourceNotFoundException;

    ErrorDTO findByHttpStatus(HttpStatus status);

    Long add(ErrorDTO errorDTO);

    void createAll(List<ErrorDTO> errorDTOs);

}

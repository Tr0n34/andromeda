package fr.andromeda.cyb.services;

import fr.andromeda.cyb.dto.errors.ErrorDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface ErrorService {

    ErrorDTO find(Long id);

    List<ErrorDTO> findAll();

    ErrorDTO findByCode(String code);

    ErrorDTO findByHttpStatus(HttpStatus status);

    Long add(ErrorDTO errorDTO);

    void delete(Long id);

    ErrorDTO update(ErrorDTO errorDTO);

}

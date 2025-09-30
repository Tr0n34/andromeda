package fr.andromeda.cyb.configurations.errors;

import fr.andromeda.cyb.exceptions.BusinessException;
import fr.andromeda.cyb.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;

public interface ErrorProvider {

    ResourceNotFoundException notFound(String entityName);

    BusinessException getException(String code, HttpStatus status, String entityName);

}

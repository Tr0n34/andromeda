package fr.andromeda.api.errors;

import fr.andromeda.api.exceptions.BusinessException;
import fr.andromeda.api.exceptions.ResourceNotFoundException;

public interface ErrorProvider {

    ResourceNotFoundException notFound(String entityName);

    BusinessException getException(String code, int status, String entityName);

}

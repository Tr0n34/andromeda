package fr.andromeda.cyb.configurations.errors;

import fr.andromeda.cyb.dto.errors.ErrorDTO;
import fr.andromeda.cyb.exceptions.ResourceNotFoundException;
import fr.andromeda.cyb.services.impl.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ErrorProviderImpl implements ErrorProvider {

    private final ErrorService errorService;

    @Autowired
    public ErrorProviderImpl(ErrorService errorService) {
        this.errorService = errorService;
    }

    @Override
    public ResourceNotFoundException notFound(Class<?> entityClass) {
        ErrorDTO errorDTO = null;
        try {
            errorDTO = errorService.findByCode("TC_NOT_FOUND");
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        String message = String.format(errorDTO.getMessage(), entityClass.getSimpleName());
        return new ResourceNotFoundException(errorDTO.getCode(), message, errorDTO.getStatus());
    }

}

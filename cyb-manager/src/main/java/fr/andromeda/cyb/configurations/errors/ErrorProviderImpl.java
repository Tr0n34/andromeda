package fr.andromeda.cyb.configurations.errors;

import fr.andromeda.cyb.dto.errors.ErrorDTO;
import fr.andromeda.cyb.exceptions.BusinessException;
import fr.andromeda.cyb.exceptions.ResourceNotFoundException;
import fr.andromeda.cyb.services.impl.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ErrorProviderImpl implements ErrorProvider {

    private final ErrorService errorService;

    @Autowired
    public ErrorProviderImpl(ErrorService errorService) {
        this.errorService = errorService;
    }

    @Override
    public ResourceNotFoundException notFound(String entityName) {
        ErrorDTO errorDTO = null;
        try {
            errorDTO = errorService.findByCode("BS_RESOURCE_NOT_FOUND");
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
        String message = String.format(errorDTO.getMessage(), entityName);
        return new ResourceNotFoundException(errorDTO.getCode(), message, errorDTO.getStatus(), entityName);
    }

    @Override
    public BusinessException getException(String code, HttpStatus status, String entityName) {
        try {
            return errorService.findAllByStatusAndEntityName(status, entityName)
                    .stream()
                    .filter(dto -> dto.getCode().equals(code))
                    .findFirst()
                    .map(dto -> new BusinessException(dto.getCode(), dto.getMessage(), dto.getStatus()))
                    .orElseThrow(RuntimeException::new);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

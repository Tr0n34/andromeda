package fr.andromeda.api.errors.impl;

import fr.andromeda.api.dto.errors.ErrorDTO;
import fr.andromeda.api.errors.ErrorProvider;
import fr.andromeda.api.exceptions.BusinessException;
import fr.andromeda.api.exceptions.ResourceNotFoundException;
import fr.andromeda.api.services.interfaces.IErrorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ErrorProviderImpl implements ErrorProvider {

    private static final Logger logger = LoggerFactory.getLogger(ErrorProviderImpl.class);

    private final IErrorService errorService;

    @Autowired
    public ErrorProviderImpl(IErrorService errorService) {
        this.errorService = errorService;
    }

    @Override
    public ResourceNotFoundException notFound(String entityName) {
        ErrorDTO errorDTO = null;
        try {
            errorDTO = errorService.findByCode("BS_RESOURCE_NOT_FOUND");
        } catch (ResourceNotFoundException e) {
            logger.error("Error not found", e);
            throw new RuntimeException(e);
        }
        String message = String.format(errorDTO.getMessage(), entityName);
        return new ResourceNotFoundException(errorDTO.getCode(), message, errorDTO.getStatus(), entityName);
    }

    @Override
    public BusinessException getException(String code, int status, String entityName) {
        try {
            return errorService.findAllByStatusAndEntityName(HttpStatus.valueOf(status), entityName)
                    .stream()
                    .filter(dto -> dto.getCode().equals(code))
                    .findFirst()
                    .map(dto -> new BusinessException(dto.getCode(), dto.getMessage(), dto.getStatus()))
                    .orElseThrow(RuntimeException::new);
        } catch (ResourceNotFoundException e) {
            logger.error("Error not found", e);
            throw new RuntimeException(e);
        }
    }

}

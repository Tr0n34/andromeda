package fr.andromeda.cyb.configurations.errors;

import fr.andromeda.api.exceptions.ResourceNotFoundException;
import fr.andromeda.cyb.dto.errors.ApiErrorDTO;
import fr.andromeda.cyb.dto.errors.ApiErrorDTOBuilder;
import fr.andromeda.cyb.exceptions.JwtGenerationException;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.oauth2.jwt.JwtEncodingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalErrorHandler.class);

    @ExceptionHandler({JwtGenerationException.class, JwtException.class, JwtEncodingException.class})
    public ResponseEntity<ApiErrorDTO> handleJwtGenerationException(Exception exception) throws ResourceNotFoundException {
        logger.error(exception.getMessage(), exception);
        return null;
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ApiErrorDTO> handle404Exception(ResourceNotFoundException exception) throws ResourceNotFoundException {
        logger.error(exception.getMessage(), exception);
        ApiErrorDTO apiErrorDTO = ApiErrorDTOBuilder.create()
                .setCode(exception.getCode())
                .setMessage(exception.getMessage())
                .setStatus(exception.getStatus())
                .setEntityName(exception.getEntityName())
                .build();
        return ResponseEntity.status(apiErrorDTO.getStatus()).body(apiErrorDTO);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ApiErrorDTO> handleHttpMessageNotReadableException(Exception exception) throws ResourceNotFoundException {
        logger.error(exception.getMessage(), exception);
        ApiErrorDTO apiErrorDTO = ApiErrorDTOBuilder.create()
                .setMessage(exception.getMessage())
                .setStatus(HttpStatus.NOT_IMPLEMENTED)
                .build();
        return ResponseEntity.status(apiErrorDTO.getStatus()).body(apiErrorDTO);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ApiErrorDTO> handleGenericException(Exception exception) throws ResourceNotFoundException {
        logger.error(exception.getMessage(), exception);
        ApiErrorDTO apiErrorDTO = ApiErrorDTOBuilder.create()
                .setMessage(exception.getMessage())
                .setStatus(HttpStatus.NOT_IMPLEMENTED)
                .build();
        return ResponseEntity.status(apiErrorDTO.getStatus()).body(apiErrorDTO);
    }

    private String format(String message, String... placeholders) {
        return String.format(message, (Object[]) placeholders);
    }

}

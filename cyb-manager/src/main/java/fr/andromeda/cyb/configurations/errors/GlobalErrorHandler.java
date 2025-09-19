package fr.andromeda.cyb.configurations.errors;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import fr.andromeda.cyb.dto.errors.ErrorBuilder;
import fr.andromeda.cyb.dto.errors.ErrorDTO;
import fr.andromeda.cyb.exceptions.BusinessException;
import fr.andromeda.cyb.exceptions.JwtGenerationException;
import fr.andromeda.cyb.exceptions.ResourceNotFoundException;
import fr.andromeda.cyb.services.impl.ErrorService;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.oauth2.jwt.JwtEncodingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalErrorHandler.class);

    @ExceptionHandler({JwtGenerationException.class, JwtException.class, JwtEncodingException.class})
    public ResponseEntity<ErrorDTO> handleJwtGenerationException(Exception exception) throws ResourceNotFoundException {
        logger.error(exception.getMessage(), exception);
        return null;
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorDTO> handle404Exception(BusinessException exception) throws ResourceNotFoundException {
        logger.error(exception.getMessage(), exception);
        ErrorDTO errorDTO = ErrorBuilder.create()
                .setCode(exception.getCode())
                .setMessage(exception.getMessage())
                .setStatus((exception.getStatus()))
                .build();
        return ResponseEntity.status(errorDTO.getStatus()).body(errorDTO);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorDTO> handleHttpMessageNotReadableException(Exception exception) throws ResourceNotFoundException {
        logger.error(exception.getMessage(), exception);
       /* ErrorDTO errorDTO = errorService.findByCode("TC_MESSAGE_NOT_READABLE");
        InvalidFormatException invalidFormatException = (InvalidFormatException) exception.getCause();
        errorDTO.setMessage(format(errorDTO.getMessage(),
                invalidFormatException.getTargetType().getSimpleName(),
                Arrays.stream(invalidFormatException.getTargetType().getEnumConstants()).map(Object::toString).collect(Collectors.joining(", "))));*/
        return null;
    }

    private String format(String message, String... placeholders) {
        return String.format(message, (Object[]) placeholders);
    }

}

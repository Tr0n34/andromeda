package fr.andromeda.cyb.configurations.errors;

import fr.andromeda.cyb.exceptions.JwtGenerationException;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtEncodingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalErrorHandler.class);

    @ExceptionHandler({JwtGenerationException.class, JwtException.class, JwtEncodingException.class})
    public ResponseEntity<ApiError> handleJwtGenerationException(Exception exception) {
        logger.error(exception.getMessage(), exception);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorBuilder.create()
                .setCode("TECH_AUTH_001")
                .setMessage("Jwt Token Error Generation")
                .setPath(exception.getMessage())
                .setStatus(HttpStatus.UNAUTHORIZED)
                .setTime(LocalDateTime.now())
                .build());
    }

}

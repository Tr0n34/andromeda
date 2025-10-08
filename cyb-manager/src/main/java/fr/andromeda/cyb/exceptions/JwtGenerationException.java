package fr.andromeda.cyb.exceptions;

import fr.andromeda.api.exceptions.BusinessException;
import org.springframework.http.HttpStatus;

public class JwtGenerationException extends BusinessException {

    public JwtGenerationException(String code, String message, HttpStatus status) {
        super(code, message, status);
    }

}

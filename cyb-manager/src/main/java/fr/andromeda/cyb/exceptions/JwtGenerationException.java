package fr.andromeda.cyb.exceptions;

import org.springframework.http.HttpStatus;

public class JwtGenerationException extends BusinessException {

    public JwtGenerationException(String code, String message, HttpStatus status) {
        super(code, message, status);
    }

}

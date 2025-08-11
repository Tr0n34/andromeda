package fr.andromeda.cyb.exceptions;

public class JwtGenerationException extends RuntimeException {

    public JwtGenerationException(String message, Throwable cause) {
        super(message, cause);
    }

}

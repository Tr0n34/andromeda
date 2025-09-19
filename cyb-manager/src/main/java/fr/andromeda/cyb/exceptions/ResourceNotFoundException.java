package fr.andromeda.cyb.exceptions;

import org.springframework.http.HttpStatus;

import javax.swing.text.html.parser.Entity;

public class ResourceNotFoundException extends BusinessException {

    public ResourceNotFoundException(String code, String message, HttpStatus status) {
        super(code, message, status);
    }

}

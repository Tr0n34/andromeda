package fr.andromeda.cyb.exceptions;

import org.springframework.http.HttpStatus;

import javax.swing.text.html.parser.Entity;

public class ResourceNotFoundException extends BusinessException {

    private String entityName;

    public ResourceNotFoundException(String code, String message, HttpStatus status, String entityName) {
        super(code, message, status);
        this.entityName = entityName;
    }

    public String getEntityName() {
        return entityName;
    }

    public ResourceNotFoundException setEntityName(String entityName) {
        this.entityName = entityName;
        return this;
    }

}

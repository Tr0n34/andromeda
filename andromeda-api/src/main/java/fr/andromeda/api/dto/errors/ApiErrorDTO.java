package fr.andromeda.api.dto.errors;

import org.springframework.http.HttpStatus;

public class ApiErrorDTO {

    private HttpStatus status;
    private String code;
    private String message;
    private String entityName;

    public HttpStatus getStatus() {
        return status;
    }

    public ApiErrorDTO setStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ApiErrorDTO setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApiErrorDTO setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getEntityName() {
        return entityName;
    }

    public ApiErrorDTO setEntityName(String entityName) {
        this.entityName = entityName;
        return this;
    }

}

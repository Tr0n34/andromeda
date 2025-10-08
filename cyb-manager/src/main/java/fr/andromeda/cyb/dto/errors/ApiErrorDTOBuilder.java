package fr.andromeda.cyb.dto.errors;

import org.springframework.http.HttpStatus;

public class ApiErrorDTOBuilder {

    private HttpStatus status;
    private String code;
    private String message;
    private String entityName;

    private ApiErrorDTOBuilder() {

    }

    public static ApiErrorDTOBuilder create() {
        return new ApiErrorDTOBuilder();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ApiErrorDTOBuilder setStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ApiErrorDTOBuilder setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApiErrorDTOBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getEntityName() {
        return entityName;
    }

    public ApiErrorDTOBuilder setEntityName(String entityName) {
        this.entityName = entityName;
        return this;
    }

    public ApiErrorDTO build() {
        return new ApiErrorDTO()
                .setCode(code)
                .setMessage(message)
                .setEntityName(entityName)
                .setStatus(status);
    }

}

package fr.andromeda.cyb.dto.errors;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorBuilder {

    private HttpStatus status;
    private String code;
    private String message;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    private ErrorBuilder() {

    }

    public static ErrorBuilder create() {
        return new ErrorBuilder();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ErrorBuilder setStatus(HttpStatus status) {
        this.status = status;
        return this;
    }


    public String getCode() {
        return code;
    }

    public ErrorBuilder setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ErrorBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public ErrorBuilder setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public ErrorBuilder setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

    public ErrorDTO build() {
        return new ErrorDTO()
                .setCode(code)
                .setMessage(message)
                .setStatus(status);
    }

}

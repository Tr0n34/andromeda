package fr.andromeda.cyb.exceptions;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    private HttpStatus status;
    private String code;
    private String message;

    public BusinessException(String code, String message, HttpStatus status, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public BusinessException(String code, String message, HttpStatus status) {
        super(message);
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public BusinessException() {
    }

    public HttpStatus getStatus() {
        return status;
    }

    public BusinessException setStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    public String getCode() {
        return code;
    }

    public BusinessException setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public BusinessException setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "BusinessException{" +
                "status=" + status +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

}

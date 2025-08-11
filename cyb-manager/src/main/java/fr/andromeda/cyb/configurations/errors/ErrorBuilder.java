package fr.andromeda.cyb.configurations.errors;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorBuilder {

    private HttpStatus status;
    private String code;
    private String message;
    private String path;
    private LocalDateTime time;

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

    public String getPath() {
        return path;
    }

    public ErrorBuilder setPath(String path) {
        this.path = path;
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

    public LocalDateTime getTime() {
        return time;
    }

    public ErrorBuilder setTime(LocalDateTime time) {
        this.time = time;
        return this;
    }

    public ApiError build() {
        return new ApiError()
                .setCode(code)
                .setTime(time)
                .setMessage(message)
                .setPath(path)
                .setStatus(status);
    }

}

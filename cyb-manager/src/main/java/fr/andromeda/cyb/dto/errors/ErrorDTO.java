package fr.andromeda.cyb.dto.errors;

import fr.andromeda.cyb.dto.IDTO;
import jakarta.persistence.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorDTO implements IDTO {

    private Long id;
    private HttpStatus status;
    private String code;
    private String message;
    private String path;
    private LocalDateTime time;

    public Long getId() {
        return id;
    }

    public ErrorDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ErrorDTO setStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ErrorDTO setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ErrorDTO setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ErrorDTO setPath(String path) {
        this.path = path;
        return this;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public ErrorDTO setTime(LocalDateTime time) {
        this.time = time;
        return this;
    }

    @Override
    public String toString() {
        return "ErrorDTO{" +
                "id=" + id +
                ", status=" + status +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                ", time=" + time +
                '}';
    }

}

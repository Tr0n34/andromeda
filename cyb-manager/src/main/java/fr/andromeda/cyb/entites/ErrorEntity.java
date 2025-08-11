package fr.andromeda.cyb.entites;

import jakarta.persistence.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Entity
public class ErrorEntity implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private HttpStatus status;
    private String code;
    private String message;
    private String path;
    private LocalDateTime time;

    public Long getId() {
        return id;
    }

    public ErrorEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ErrorEntity setStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ErrorEntity setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ErrorEntity setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ErrorEntity setPath(String path) {
        this.path = path;
        return this;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public ErrorEntity setTime(LocalDateTime time) {
        this.time = time;
        return this;
    }
}

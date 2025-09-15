package fr.andromeda.cyb.entites.errors;

import fr.andromeda.cyb.entites.IEntity;
import jakarta.persistence.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "errors")
public class Error implements IEntity {

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

    public Error setId(Long id) {
        this.id = id;
        return this;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Error setStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Error setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Error setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Error setPath(String path) {
        this.path = path;
        return this;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Error setTime(LocalDateTime time) {
        this.time = time;
        return this;
    }
}

package fr.andromeda.cyb.entites.errors;

import fr.andromeda.cyb.entites.IEntity;
import fr.andromeda.cyb.entites.TimeHandlerEntity;
import jakarta.persistence.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "errors")
public class Error implements IEntity, TimeHandlerEntity<Error> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private HttpStatus status;
    private String code;
    private String message;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

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


    @Override
    public LocalDateTime getCreatedOn() {
        return  createdOn;
    }

    @Override
    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    @Override
    public Error setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    @Override
    public Error setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

}

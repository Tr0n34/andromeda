package fr.andromeda.cyb.entites.errors;

import fr.andromeda.api.entities.AuditableEntity;
import fr.andromeda.api.entities.IEntity;
import jakarta.persistence.*;
import org.springframework.http.HttpStatus;

@Entity
@Table(name = "errors")
public class Error extends AuditableEntity implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private HttpStatus status;
    private String code;
    private String message;
    private String entityName;

    public Long getId() {
        return id;
    }

    public Error setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEntityName() {
        return entityName;
    }

    public Error setEntityName(String entityName) {
        this.entityName = entityName;
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

}

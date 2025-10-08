package fr.andromeda.api.dto.errors;

import fr.andromeda.api.dto.AuditableDTO;
import fr.andromeda.api.dto.IDTO;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorDTO implements IDTO, AuditableDTO {

    private Long id;
    private HttpStatus status;
    private String code;
    private String message;
    private String entityName;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

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

    public String getEntityName() {
        return entityName;
    }

    public ErrorDTO setEntityName(String entityName) {
        this.entityName = entityName;
        return this;
    }

    @Override
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public ErrorDTO setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    @Override
    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public ErrorDTO setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

}

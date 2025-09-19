package fr.andromeda.cyb.dto.errors;

import fr.andromeda.cyb.dto.interfaces.IDTO;
import fr.andromeda.cyb.dto.interfaces.TimeHandlerDTO;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorDTO implements IDTO, TimeHandlerDTO<ErrorDTO> {

    private Long id;
    private HttpStatus status;
    private String code;
    private String message;
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

    @Override
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    @Override
    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    @Override
    public ErrorDTO setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    @Override
    public ErrorDTO setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

}

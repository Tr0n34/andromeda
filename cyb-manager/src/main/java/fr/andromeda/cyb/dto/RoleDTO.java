package fr.andromeda.cyb.dto;

import fr.andromeda.api.dto.AuditableDTO;
import fr.andromeda.api.dto.IDTO;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;

public class RoleDTO implements IDTO, GrantedAuthority, AuditableDTO {

    private Long id;
    private String authority;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    public Long getId() {
        return id;
    }

    public RoleDTO setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public RoleDTO setAuthority(String authority) {
        this.authority = authority;
        return this;
    }

    @Override
    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public RoleDTO setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    @Override
    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public RoleDTO setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
        return this;
    }

}

package fr.andromeda.cyb.dto;

import fr.andromeda.cyb.dto.interfaces.AuditableDTO;
import fr.andromeda.cyb.dto.interfaces.IDTO;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

package fr.andromeda.cyb.dto;

import fr.andromeda.cyb.dto.interfaces.IDTO;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

public class RoleDTO implements IDTO, GrantedAuthority {

    private Long id;
    private String authority;
    private Set<UserDTO> users = new HashSet<>();

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

    public Set<UserDTO> getUsers() {
        return users;
    }

    public RoleDTO setUsers(Set<UserDTO> users) {
        this.users = users;
        return this;
    }

}

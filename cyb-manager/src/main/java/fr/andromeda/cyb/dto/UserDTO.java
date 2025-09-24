package fr.andromeda.cyb.dto;

import fr.andromeda.cyb.dto.interfaces.IDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

public class UserDTO implements UserDetails, IDTO {

    private Long id;
    private String username;
    private String password;
    private String lastName;
    private String firstName;
    private String email;
    private boolean locked;
    private Set<RoleDTO> roles;
    private LocalDateTime createdOn;
    private LocalDateTime expiredOn;

    public Long getId() {
        return id;
    }

    public UserDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public boolean isLocked() {
        return locked;
    }

    public UserDTO setLocked(boolean locked) {
        this.locked = locked;
        return this;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public UserDTO setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public UserDTO setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public LocalDateTime getExpiredOn() {
        return expiredOn;
    }

    public UserDTO setExpiredOn(LocalDateTime expiredOn) {
        this.expiredOn = expiredOn;
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        boolean isExpired = false;
        LocalDateTime now = LocalDateTime.now();
        if ( expiredOn == null || expiredOn.isAfter(LocalDateTime.now())) {
            isExpired = true;
        }
        return !isExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isAccountNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return !locked;
    }

}

package fr.andromeda.cyb.dto;

import org.springframework.security.core.userdetails.UserDetails;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

public class UserDTO implements UserDetails, IDTO {

    private String username;
    private String password;
    private String lastName;
    private String firstName;
    private String email;
    private boolean locked;
    private Set<RoleDTO> roles;
    private LocalDateTime createdOn;
    private LocalDateTime lastExpiredOn;
    private Duration accountExpirationDuration;
    private Duration passwordExpirationDuration;


    @Override
    public Collection<RoleDTO> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return "";
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        boolean isExpired = false;
        LocalDateTime now = LocalDateTime.now();
        if ( lastExpiredOn == null && createdOn.plus(accountExpirationDuration).isAfter(now) ) {
            isExpired = true;
        } else if (lastExpiredOn != null && lastExpiredOn.plus(accountExpirationDuration).isAfter(now)) {
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
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

}

package fr.andromeda.cyb.dto.authentification;

import fr.andromeda.cyb.dto.RoleDTO;

import java.util.Set;

public class TokenAuthenticationDTO {

    private String token;
    private String refreshToken;
    private Set<RoleDTO> roles;

    public TokenAuthenticationDTO(String token, String refreshToken, Set<RoleDTO> roles) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.roles = roles;
    }

    public TokenAuthenticationDTO() {

    }

    public String getToken() {
        return token;
    }

    public TokenAuthenticationDTO setToken(String token) {
        this.token = token;
        return this;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public TokenAuthenticationDTO setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public TokenAuthenticationDTO setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
        return this;
    }

    @Override
    public String toString() {
        return "TokenAuthenticationDTO{" +
                "token='" + token + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", roles=" + roles +
                '}';
    }

}

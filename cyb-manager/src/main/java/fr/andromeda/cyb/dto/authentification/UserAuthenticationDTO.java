package fr.andromeda.cyb.dto.authentification;

import fr.andromeda.cyb.dto.interfaces.IDTO;

public class UserAuthenticationDTO implements IDTO {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public UserAuthenticationDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserAuthenticationDTO setPassword(String password) {
        this.password = password;
        return this;
    }

}

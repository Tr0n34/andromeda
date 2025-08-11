package fr.andromeda.cyb.dto.authentification;

import fr.andromeda.cyb.dto.IDTO;

public class RefreshTokenDTO implements IDTO {

    private String refreshToken;

    public RefreshTokenDTO(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public RefreshTokenDTO setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    @Override
    public String toString() {
        return "RefreshTokenDTO{" +
                "refreshToken='" + refreshToken + '\'' +
                '}';
    }

}

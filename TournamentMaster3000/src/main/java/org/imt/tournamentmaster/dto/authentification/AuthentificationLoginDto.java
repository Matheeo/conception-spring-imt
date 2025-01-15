package org.imt.tournamentmaster.dto.authentification;

import jakarta.validation.constraints.NotBlank;

public class AuthentificationLoginDto {

    @NotBlank(message = "Le token est obligatoire")
    private String token;

    public void setToken(@NotBlank(message = "Le token est obligatoire") String token) {
        this.token = token;
    }

    public @NotBlank(message = "Le token est obligatoire") String getToken() {
        return token;
    }
}

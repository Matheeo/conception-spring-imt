package org.imt.tournamentmaster.dto.utilisateur;

import jakarta.validation.constraints.NotBlank;

public class UtilisateurLoginDto {

    @NotBlank(message = "L'email ne peux pas etre vide")
    private String email;

    @NotBlank(message = "Le mot de passe ne peux pas etre vide")
    private String mot_de_passe;

    public @NotBlank(message = "L'email ne peux pas etre vide") String getEmail() {
        return email;
    }

    public @NotBlank(message = "Le mot de passe ne peux pas etre vide") String getMot_de_passe() {
        return mot_de_passe;
    }
}

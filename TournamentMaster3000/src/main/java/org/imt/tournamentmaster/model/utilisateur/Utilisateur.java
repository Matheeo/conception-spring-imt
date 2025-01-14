package org.imt.tournamentmaster.model.utilisateur;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Utilisateur {

    @JsonIgnore
    @Id
    private long id;

    private String nom;

    private String prenom;

    private String mot_de_passe;

    private String email;

    private boolean est_administrateur;

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEst_administrateur() {
        return est_administrateur;
    }
}

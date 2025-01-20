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

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEst_administrateur(boolean est_administrateur) {
        this.est_administrateur = est_administrateur;
    }

    public void setId(long id) {
        this.id = id;
    }
}

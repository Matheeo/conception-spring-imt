package org.imt.tournamentmaster.model.utilisateur;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}

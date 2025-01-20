package org.imt.tournamentmaster.repository.utilisateur;

import jakarta.validation.constraints.NotBlank;
import org.imt.tournamentmaster.model.utilisateur.Utilisateur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> {
    Utilisateur getUtilisateurByEmail(@NotBlank(message = "L'email ne peux pas etre vide") String email);
}

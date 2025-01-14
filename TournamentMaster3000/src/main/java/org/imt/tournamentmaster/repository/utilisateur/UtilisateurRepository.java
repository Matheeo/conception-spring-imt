package org.imt.tournamentmaster.repository.utilisateur;

import org.imt.tournamentmaster.model.utilisateur.Utilisateur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> {
}

package org.imt.tournamentmaster.service.utilisateur;

import org.imt.tournamentmaster.model.utilisateur.Utilisateur;
import org.imt.tournamentmaster.repository.utilisateur.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Transactional(readOnly = true)
    public List<Utilisateur> getAll() {
        return StreamSupport.stream(utilisateurRepository.findAll().spliterator(), false)
                .toList();
    }
}

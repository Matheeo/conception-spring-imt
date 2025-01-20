package org.imt.tournamentmaster.service.equipe;

import org.imt.tournamentmaster.dto.equipe.JoueurDto;
import org.imt.tournamentmaster.model.equipe.Joueur;
import org.imt.tournamentmaster.repository.equipe.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class JoueurService {

    private final JoueurRepository joueurRepository;

    @Autowired
    public JoueurService(JoueurRepository joueurRepository) {
        this.joueurRepository = joueurRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Joueur> getById(long id) {
        return joueurRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Joueur> getAll() {
        return StreamSupport.stream(joueurRepository.findAll().spliterator(), false)
                .toList();
    }

    public List<Joueur> saveJoueurs(List<JoueurDto> dtoList){
        List<Joueur> joueurList = new ArrayList<>();

        for (JoueurDto joueurDto: dtoList){
            Joueur joueur = new Joueur();
            joueur.setNumero(joueurDto.getNumero());
            joueur.setPrenom(joueurDto.getPrenom());
            joueur.setNom(joueurDto.getNom());
            joueurList.add(joueur);
        }
        return (List<Joueur>) joueurRepository.saveAll(joueurList);
    }
}

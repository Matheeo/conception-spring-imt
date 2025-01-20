package org.imt.tournamentmaster.service.equipe;

import org.imt.tournamentmaster.dto.equipe.EquipeDto;
import org.imt.tournamentmaster.dto.equipe.JoueurDto;
import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.model.equipe.Joueur;
import org.imt.tournamentmaster.repository.equipe.EquipeRepository;
import org.imt.tournamentmaster.repository.equipe.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class EquipeService {

    private final EquipeRepository equipeRepository;
    private final JoueurService joueurService;

    @Autowired
    public EquipeService(EquipeRepository equipeRepository, JoueurService joueurService) {
        this.equipeRepository = equipeRepository;
        this.joueurService = joueurService;
    }

    @Transactional(readOnly = true)
    public Optional<Equipe> getById(long id) {
        return equipeRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Equipe> getAll() {
        return StreamSupport.stream(equipeRepository.findAll().spliterator(), false)
                .toList();
    }

    @Transactional
    public List<Equipe> saveEquipes(List<EquipeDto> dtoList){
        List<Equipe> equipeList = new ArrayList<>();

        for (EquipeDto equipeDto: dtoList){

            if (equipeDto.getJoueurs() == null){
                throw new RuntimeException("Aucun joueur a inséré dans l'equipe " + equipeDto.getNom() + "!");
            }

            List<Joueur> joueurList = joueurService.saveJoueurs(equipeDto.getJoueurs());

            if (joueurList == null){
                throw new RuntimeException("Probleme dans l'insertion des joueurs");
            }

            Equipe equipe = new Equipe();
            equipe.setJoueurs(joueurList);
            equipe.setNom(equipeDto.getNom());
            equipeList.add(equipe);
        }
        return (List<Equipe>) equipeRepository.saveAll(equipeList);
    }
}

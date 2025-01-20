package org.imt.tournamentmaster.service.match;

import org.imt.tournamentmaster.dto.equipe.EquipeDto;
import org.imt.tournamentmaster.dto.match.MatchDto;
import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.model.match.Round;
import org.imt.tournamentmaster.model.resultat.Resultat;
import org.imt.tournamentmaster.repository.equipe.EquipeRepository;
import org.imt.tournamentmaster.repository.match.MatchRepository;
import org.imt.tournamentmaster.repository.match.RoundRepository;
import org.imt.tournamentmaster.repository.resultat.ResultatRepository;
import org.imt.tournamentmaster.service.equipe.EquipeService;
import org.imt.tournamentmaster.service.resultat.ResultatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final ResultatService resultatService;
    private final EquipeService equipeService;
    private final RoundService roundService;
    private final ResultatRepository resultatRepository;
    private final RoundRepository roundRepository;
    private final EquipeRepository equipeRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository, ResultatService resultatService, EquipeService equipeService, RoundService roundService, ResultatRepository resultatRepository, RoundRepository roundRepository, EquipeRepository equipeRepository) {
        this.matchRepository = matchRepository;
        this.resultatService = resultatService;
        this.equipeService = equipeService;
        this.roundService = roundService;
        this.resultatRepository = resultatRepository;
        this.roundRepository = roundRepository;
        this.equipeRepository = equipeRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Match> getById(long id) {
        return matchRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Match> getAll() {
        return StreamSupport.stream(matchRepository.findAll().spliterator(), false)
                .toList();
    }

    public boolean deleteById(long id) {
        Match match = matchRepository.findById(id).orElse(null);

        if (match != null) {
            equipeRepository.delete(match.getEquipeA());
            equipeRepository.delete(match.getEquipeB());
            resultatRepository.deleteByMatch(match);
            matchRepository.delete(match);

            return true;
        }
        return false;
    }


    @Transactional
    public List<Match> saveMatchs(List<MatchDto> dtoList){
        List<Match> matchList = new ArrayList<>();

        // Parcours les matchs
        for (MatchDto matchDto: dtoList){

            // Insere les dto match pour récupérer leur id apres
            List<EquipeDto> equipeDtoList = new ArrayList<>();
            equipeDtoList.add(matchDto.getEquipeA());
            equipeDtoList.add(matchDto.getEquipeB());
            List<Equipe> equipeList = equipeService.saveEquipes(equipeDtoList);

            // Si on a bien les deux equipes existant
            if (equipeList.size() == 2){

                // On enregistre les rounds
                List<Round> roundList = roundService.saveRounds(matchDto.getRounds());

                // On creer l'entity match avant de l'ajouter dans la list de match
                Match match = new Match();
                match.setStatus(matchDto.getStatus());
                match.setRounds(roundList);
                match.setEquipeA(equipeList.get(0));
                match.setEquipeB(equipeList.get(1));
                matchList.add(match);
            } else {
                throw new RuntimeException("Erreur dans l'insertion des equipes");
            }
        }

        // On sauvegarde tout les element insérés
        List<Match> matchesSaved = (List<Match>) matchRepository.saveAll(matchList);

        // On enregistre dans resultat
        try {
            List<Resultat> resultats = new ArrayList<>();
            for (Match match: matchesSaved){
                Resultat resultat = new Resultat();
                resultat.setMatch(match);
                resultats.add(resultat);
            }
            resultatRepository.saveAll(resultats);
        } catch (Exception e){
            throw new RuntimeException("Erreur survenu dans l'ajout des resultats pour les match terminer");
        }

        return matchesSaved;
    }
}

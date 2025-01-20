package org.imt.tournamentmaster.service.match;

import org.imt.tournamentmaster.dto.match.RoundDto;
import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.model.match.Round;
import org.imt.tournamentmaster.repository.match.RoundRepository;
import org.imt.tournamentmaster.service.equipe.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class RoundService {

    private final RoundRepository roundRepository;
    private final EquipeService equipeService;

    @Autowired
    public RoundService(RoundRepository roundRepository, EquipeService equipeService) {
        this.roundRepository = roundRepository;
        this.equipeService = equipeService;
    }

    public Optional<Round> getById(long id) {
        return roundRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Round> getAll() {
        return StreamSupport.stream(roundRepository.findAll().spliterator(), false)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<Round> getByScoreAGreaterThanEqual(int scoreA) {
        return roundRepository.findByScoreAGreaterThanEqual(scoreA);
    }

    @Transactional
    public List<Round> saveRounds(List<RoundDto> dtoList){
        List<Round> roundList = new ArrayList<>();

        // Parcours tout les dto de round
        for (RoundDto roundDto: dtoList){

            // On fait un get sur l'id des equipes
            Optional<Equipe> equipeA = equipeService.getById(roundDto.getEquipeA());
            Optional<Equipe> equipeB = equipeService.getById(roundDto.getEquipeB());

            // Si on a bien les deux equipes existant
            if (equipeA.isPresent() && equipeB.isPresent()){

                // On creer l'entity round et on l'ajoute a la liste qui sera inséré a la fin
                Round round = new Round();
                round.setEquipeA(equipeA.get());
                round.setEquipeB(equipeB.get());
                round.setScoreA(roundDto.getScoreA());
                round.setScoreB(roundDto.getScoreB());
                round.setRoundNumber(roundDto.getRoundNumber());
                roundList.add(round);
            }
        }

        return (List<Round>) roundRepository.saveAll(roundList);
    }
}

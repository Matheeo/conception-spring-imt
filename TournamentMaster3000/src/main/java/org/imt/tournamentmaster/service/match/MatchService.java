package org.imt.tournamentmaster.service.match;

import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.model.resultat.Resultat;
import org.imt.tournamentmaster.repository.match.MatchRepository;
import org.imt.tournamentmaster.service.resultat.ResultatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final ResultatService resultatService;

    @Autowired
    public MatchService(MatchRepository matchRepository, ResultatService resultatService) {
        this.matchRepository = matchRepository;
        this.resultatService = resultatService;
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
        if (matchRepository.existsById(id)) {
            List<Resultat> resultsToDelete = resultatService.getByMatchId(id);
            for (Resultat resultat : resultsToDelete) {
                resultatService.deleteById(resultat.getId());
            }
            matchRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

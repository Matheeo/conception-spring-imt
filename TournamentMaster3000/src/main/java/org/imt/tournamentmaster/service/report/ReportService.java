package org.imt.tournamentmaster.service.report;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.imt.tournamentmaster.dto.MatchDto;
import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.model.match.Round;
import org.imt.tournamentmaster.model.report.Report;
import org.imt.tournamentmaster.repository.equipe.EquipeRepository;
import org.imt.tournamentmaster.repository.match.MatchRepository;
import org.imt.tournamentmaster.repository.match.RoundRepository;
import org.imt.tournamentmaster.repository.report.ReportRepository;
import org.imt.tournamentmaster.service.match.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final EquipeRepository equipeRepository;
    private final RoundRepository roundRepository;
    private final MatchRepository matchRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository, MatchService matchService, EquipeRepository equipeRepository, RoundRepository roundRepository, MatchRepository matchRepository) {
        this.reportRepository = reportRepository;
        this.equipeRepository = equipeRepository;
        this.roundRepository = roundRepository;
        this.matchRepository = matchRepository;
    }

    @Transactional(readOnly = true)
    public List<Report> getAll() {
        return StreamSupport.stream(reportRepository.findAll().spliterator(), false)
                .toList();
    }

    @Transactional
    public String importMatches(List<MatchDto> matches) {
        Report report = new Report();
        int matchNumber = 0;
        List<MatchDto> matchesAlreadyCheck = new ArrayList<>();

        List<String> successfulMatches = new ArrayList<>();
        List<String> skippedMatches = new ArrayList<>();
        List<String> failedMatches = new ArrayList<>();

        for (MatchDto match : matches) {
            boolean sameMatch = false;
            matchNumber++;

            for( MatchDto matchAlreadyCheck : matchesAlreadyCheck){
                if(match.equals(matchAlreadyCheck)){
                    skippedMatches.add("Match : " + matchNumber+ " skipped. Reason: Match already exists");
                    sameMatch = true;
                }
            }

            if (equipeRepository.findById(match.getIdEquipeA()).isEmpty() ||
                    equipeRepository.findById(match.getIdEquipeB()).isEmpty()) {
                failedMatches.add("Match : " + matchNumber + " failed. Reason: Equipe does not exist");
                continue;
            }

            Equipe equipeA = equipeRepository.findById(match.getIdEquipeA()).get();
            Equipe equipeB = equipeRepository.findById(match.getIdEquipeB()).get();

            if (equipeA.equals(equipeB)) {
                failedMatches.add("Match : " + matchNumber + " failed. Reason: Equipe A and Equipe B are the same");
                continue;
            }


            List<Round> rounds = match.getRounds().stream().map(roundDto -> {
                Round round = new Round();
                round.setId(Math.abs(UUID.randomUUID().getMostSignificantBits()));
                round.setScoreA(roundDto.getScoreA());
                round.setScoreB(roundDto.getScoreB());
                round.setRoundNumber(roundDto.getRoundNumber());
                round.setEquipeA(equipeA);
                round.setEquipeB(equipeB);
                return roundRepository.save(round);
            }).collect(Collectors.toList());

            Match newMatch = new Match();
            newMatch.setId(Math.abs(UUID.randomUUID().getMostSignificantBits()));
            newMatch.setEquipeA(equipeA);
            newMatch.setEquipeB(equipeB);
            newMatch.setRounds(rounds);
            newMatch.setStatus(Match.Status.valueOf(match.getStatus()));

            for (Match existMatch : matchRepository.findAll()){
                if(existMatch.equals(newMatch)){
                    skippedMatches.add("Match : " + matchNumber+ " skipped. Reason: Match already exists");
                    sameMatch = true;
                }
            }

            if (!sameMatch) {
                matchRepository.save(newMatch);
                successfulMatches.add("Match : " + matchNumber + " successfully added. Winner : " + newMatch.determineWinner().getNom());
            }

            matchesAlreadyCheck.add(match);
        }


        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String successfulMatchesJson = objectMapper.writeValueAsString(successfulMatches);
            String skippedMatchesJson = objectMapper.writeValueAsString(skippedMatches);
            String failedMatchesJson = objectMapper.writeValueAsString(failedMatches);

            report.setImportDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()));
            report.setNumberOfEvents(successfulMatches.size() + skippedMatches.size() + failedMatches.size());
            report.setSuccessfulMatches(successfulMatches.size());
            report.setSkippedMatches(skippedMatches.size());
            report.setFailedMatches(failedMatches.size());
            report.setSuccessfulMatchesDetails(successfulMatchesJson);
            report.setSkippedMatchesDetails(skippedMatchesJson);
            report.setFailedMatchesDetails(failedMatchesJson);
        } catch (JsonProcessingException e) {
            return null;
        }

        reportRepository.save(report);
        try {
            return objectMapper.writeValueAsString(report);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}

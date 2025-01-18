package org.imt.tournamentmaster.dto;

import java.util.List;
import java.util.Objects;

public class MatchDto {
    private Long idEquipeA;
    private Long idEquipeB;
    private List<RoundDto> rounds;
    private String status;


    public Long getIdEquipeA() {
        return idEquipeA;
    }

    public void setIdEquipeA(Long idEquipeA) {
        this.idEquipeA = idEquipeA;
    }

    public Long getIdEquipeB() {
        return idEquipeB;
    }

    public void setIdEquipeB(Long idEquipeB) {
        this.idEquipeB = idEquipeB;
    }

    public List<RoundDto> getRounds() {
        return rounds;
    }

    public void setRounds(List<RoundDto> rounds) {
        this.rounds = rounds;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MatchDto matchDto = (MatchDto) o;

        return Objects.equals(idEquipeA, matchDto.idEquipeA) &&
                Objects.equals(idEquipeB, matchDto.idEquipeB) &&
                Objects.equals(rounds, matchDto.rounds) &&
                Objects.equals(status, matchDto.status);
    }
}
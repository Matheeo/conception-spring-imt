package org.imt.tournamentmaster.dto;

public class RoundDto {
    private int scoreA;
    private int scoreB;
    private int roundNumber;

    public int getScoreA() {
        return scoreA;
    }

    public void setScoreA(int scoreA) {
        this.scoreA = scoreA;
    }

    public int getScoreB() {
        return scoreB;
    }

    public void setScoreB(int scoreB) {
        this.scoreB = scoreB;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Vérification si les deux objets sont identiques
        if (o == null || getClass() != o.getClass()) return false; // Vérification du type d'objet

        RoundDto roundDto = (RoundDto) o; // Cast de l'objet comparé

        // Comparaison des attributs
        return scoreA == roundDto.scoreA &&
                scoreB == roundDto.scoreB &&
                roundNumber == roundDto.roundNumber;
    }
}

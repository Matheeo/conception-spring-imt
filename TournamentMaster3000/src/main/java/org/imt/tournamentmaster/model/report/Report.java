package org.imt.tournamentmaster.model.report;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Report {
    @JsonIgnore
    @Id
    private long id;

    private String importDate;
    private int numberOfEvents;
    private int successfulMatches;
    private int skippedMatches;
    private int failedMatches;
    private String successfulMatchesDetails;
    private String skippedMatchesDetails;
    private String failedMatchesDetails;

    // Getters and Setters

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public int getNumberOfEvents() {
        return numberOfEvents;
    }

    public void setNumberOfEvents(int numberOfEvents) {
        this.numberOfEvents = numberOfEvents;
    }

    public int getSuccessfulMatches() {
        return successfulMatches;
    }

    public void setSuccessfulMatches(int successfulMatches) {
        this.successfulMatches = successfulMatches;
    }

    public int getSkippedMatches() {
        return skippedMatches;
    }

    public void setSkippedMatches(int skippedMatches) {
        this.skippedMatches = skippedMatches;
    }

    public int getFailedMatches() {
        return failedMatches;
    }

    public void setFailedMatches(int failedMatches) {
        this.failedMatches = failedMatches;
    }

    public String getSuccessfulMatchesDetails() {
        return successfulMatchesDetails;
    }

    public void setSuccessfulMatchesDetails(String successfulMatchesDetails) {
        this.successfulMatchesDetails = successfulMatchesDetails;
    }

    public String getSkippedMatchesDetails() {
        return skippedMatchesDetails;
    }

    public void setSkippedMatchesDetails(String skippedMatchesDetails) {
        this.skippedMatchesDetails = skippedMatchesDetails;
    }

    public String getFailedMatchesDetails() {
        return failedMatchesDetails;
    }

    public void setFailedMatchesDetails(String failedMatchesDetails) {
        this.failedMatchesDetails = failedMatchesDetails;
    }
}
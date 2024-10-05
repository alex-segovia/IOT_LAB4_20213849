package com.example.lab4_20213849.Dtos;

import java.io.Serializable;

public class Event implements Serializable {
    private String strEvent;
    private String intRound;
    private String strHomeTeam;
    private String strAwayTeam;
    private String intHomeScore;
    private String intAwayScore;
    private String dateEvent;
    private String strLeagueBadge;
    private String intSpectators;

    public String getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(String dateEvent) {
        this.dateEvent = dateEvent;
    }

    public String getIntAwayScore() {
        return intAwayScore;
    }

    public void setIntAwayScore(String intAwayScore) {
        this.intAwayScore = intAwayScore;
    }

    public String getIntHomeScore() {
        return intHomeScore;
    }

    public void setIntHomeScore(String intHomeScore) {
        this.intHomeScore = intHomeScore;
    }

    public String getIntRound() {
        return intRound;
    }

    public void setIntRound(String intRound) {
        this.intRound = intRound;
    }

    public String getIntSpectators() {
        return intSpectators;
    }

    public void setIntSpectators(String intSpectators) {
        this.intSpectators = intSpectators;
    }

    public String getStrAwayTeam() {
        return strAwayTeam;
    }

    public void setStrAwayTeam(String strAwayTeam) {
        this.strAwayTeam = strAwayTeam;
    }

    public String getStrEvent() {
        return strEvent;
    }

    public void setStrEvent(String strEvent) {
        this.strEvent = strEvent;
    }

    public String getStrHomeTeam() {
        return strHomeTeam;
    }

    public void setStrHomeTeam(String strHomeTeam) {
        this.strHomeTeam = strHomeTeam;
    }

    public String getStrLeagueBadge() {
        return strLeagueBadge;
    }

    public void setStrLeagueBadge(String strLeagueBadge) {
        this.strLeagueBadge = strLeagueBadge;
    }
}

package com.example.lab4_20213849.Dtos;

import java.io.Serializable;

public class LeagueDto implements Serializable {
    private League[] leagues;
    private League[] countries;

    public League[] getLeagues() {
        return leagues;
    }

    public void setLeagues(League[] leagues) {
        this.leagues = leagues;
    }

    public League[] getCountries() {
        return countries;
    }

    public void setCountries(League[] countries) {
        this.countries = countries;
    }
}

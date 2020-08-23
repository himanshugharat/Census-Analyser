package com.bridglabz;

public class USCensusDAO {
    public String state;
    public String population;

    public USCensusDAO(USCensusCSV usCensusCSV) {
        state = usCensusCSV.state;
        population = usCensusCSV.population;

    }
}

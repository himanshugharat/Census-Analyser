package com.bridglabz;

public class USCensusDAO {
    public String populationDensity;
    public String area;
    public String state;
    public String population;

    public USCensusDAO(USCensusCSV usCensusCSV) {
        state = usCensusCSV.state;
        population = usCensusCSV.population;
        populationDensity = usCensusCSV.populationDensity;
        area = usCensusCSV.area;

    }
}

package com.bridglabz;

public class CensusDAO {
    public String totalArea;
    public String usPopulation;
    public String populationDensity;
    public String stateCode;
    public String state;
    public int densityPerSqKm;
    public String areaInSqKm;
    public int population;

    public CensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
        population = indiaCensusCSV.population;
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
        areaInSqKm = indiaCensusCSV.areaInSqKm;
    }

    public CensusDAO(IndiaStateCodeCSV indiaStateCodeCSV) {
        stateCode = indiaStateCodeCSV.stateCode;
        state = indiaStateCodeCSV.stateName;
    }

    public CensusDAO(USCensusCSV usCensusCSV) {
        totalArea = usCensusCSV.area;
        usPopulation = usCensusCSV.population;
        populationDensity = usCensusCSV.populationDensity;
        state = usCensusCSV.state;
    }
}

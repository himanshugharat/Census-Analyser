package com.bridglabz;

public class IndiaCensusDAO {

    public String state;
    public int densityPerSqKm;
    public int area;
    public int population;

    public IndiaCensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
        population = indiaCensusCSV.population;
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
        area = indiaCensusCSV.areaInSqKm;
    }



}

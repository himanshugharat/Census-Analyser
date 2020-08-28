package com.bridglabz;

import com.opencsv.bean.CsvBindByName;

public class USCensusCSV {
    @CsvBindByName(column = "State")
    public String state;
    @CsvBindByName(column = "Population")
    public String population;
    @CsvBindByName(column = "Population Density")
    public String populationDensity;
    @CsvBindByName(column = "Land area")
    public String area;
}

package com.bridglabz;

import com.opencsv.bean.CsvBindByName;

public class USCensusCSV {
    @CsvBindByName(column = "State")
    public String state;
    @CsvBindByName(column = "StateId")
    public String stateId;
    @CsvBindByName(column = "Population")
    public String population;
}

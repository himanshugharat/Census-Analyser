package com.bridglabz;

public class IndiaStateCodeDAO {
    public String stateName;
    public String stateCode;

    public IndiaStateCodeDAO(IndiaStateCodeCSV indiaStateCodeCSV) {
        stateCode = indiaStateCodeCSV.stateCode;
        stateName = indiaStateCodeCSV.stateName;
    }
}

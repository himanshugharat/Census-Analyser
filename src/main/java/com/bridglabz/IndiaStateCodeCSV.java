package com.bridglabz;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCodeCSV {
    @CsvBindByName(column = "State")
    public String stateName;

    @CsvBindByName(column = "StateCode")
    public String stateCode;

    @Override
    public String toString() {
        return "IndiaStateCodeCSV{" +
                "stateName='" + stateName + '\'' +
                ", stateCode=" + stateCode +
                '}';
    }
}

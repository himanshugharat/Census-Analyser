package com.bridglabz;

public class CensusAnalyserException extends Exception {
    enum ExecptionType {
        CENSUS_FILE_PROBLEM, FILE_TYPE_PROBLEM, FILE_HEADER_OR_DELIMITER_PROBLEM, NO_DATA,INVALID_COUNTRY;
    }
    ExecptionType type;

    public CensusAnalyserException(String message, String name) {
        super(message);
        this.type = ExecptionType.valueOf(name);
    }

    public CensusAnalyserException(String message, ExecptionType type) {
        super(message);
        this.type = type;
    }

    public CensusAnalyserException(String message, ExecptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
}

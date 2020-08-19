package com.bridglabz;

public class CensusAnalyserException extends Exception {
    enum ExecptionType {
        CENSUS_FILE_PROBLEM, FILE_TYPE_OR_DELIMITER_PROBLEM
    }

    ExecptionType type;

    public CensusAnalyserException(String message, ExecptionType type) {
        super(message);
        this.type = type;
    }

    public CensusAnalyserException(String message, ExecptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }

}

package com.bridglabz;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {
    private static final String INDIA_CENSUS_CSV_FILE_PATH = ".\\src\\test\\resources\\IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = ".\\src\\main\\resources\\IndiaStateCensusData.csv";
    private static final String CSV_FILE_WITH_WRONG_HEADER_PATH = ".\\src\\test\\resources\\Delimiter.csv";
    private static final String CSV_FILE_WITH_WRONG_TYPE_PATH = ".\\src\\test\\resources\\file.txt";
    private static final String INDIA_STATE_CSV_FILE_PATH = ".\\src\\test\\resources\\IndiaStateCode.csv";
    private static final String US_CENSUS_CSV_FILE_PATH = ".\\src\\test\\resources\\USCensusData.csv";


    @Test
    public void givenIndianCensusCsvFile_WhenCheked_ReturnCorrectRecords() {
        try {
            CensusAnalyser censusAanlyser = new CensusAnalyser();
            int numberOfRecords = censusAanlyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numberOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExecptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(CSV_FILE_WITH_WRONG_HEADER_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExecptionType.FILE_TYPE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(CSV_FILE_WITH_WRONG_HEADER_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExecptionType.FILE_HEADER_OR_DELIMITER_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(CSV_FILE_WITH_WRONG_HEADER_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExecptionType.FILE_HEADER_OR_DELIMITER_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCsvFile_WhenChecked_ReturnCorrectRecords() {
        try {
            CensusAnalyser censusAanlyser = new CensusAnalyser();
            int numberOfRecords = censusAanlyser.loadIndiaStateData(INDIA_STATE_CSV_FILE_PATH);
            Assert.assertEquals(37, numberOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndiaStateData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExecptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateData_WithWrongType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateData(CSV_FILE_WITH_WRONG_TYPE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExecptionType.FILE_TYPE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateData_WithWrongDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateData(CSV_FILE_WITH_WRONG_HEADER_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExecptionType.FILE_HEADER_OR_DELIMITER_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateData_WithWrongHeader_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaStateData(CSV_FILE_WITH_WRONG_HEADER_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExecptionType.FILE_HEADER_OR_DELIMITER_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
        IndiaCensusCSV[] CensusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
        Assert.assertEquals("Andhra Pradesh", CensusCSV[0].state);
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData();
        IndiaCensusCSV[] CensusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
        Assert.assertEquals(607688, CensusCSV[0].population);
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnDensityPerKm_ShouldReturnSortedResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.getDensityWiseSortedCensusData();
        IndiaCensusCSV[] CensusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
        Assert.assertEquals(50, CensusCSV[0].densityPerSqKm);
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnAreaPerSQKM_ShouldReturnSortedResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.getAreaWiseSortedCensusData();
        IndiaCensusCSV[] CensusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
        Assert.assertEquals("10486", CensusCSV[0].areaInSqKm);
    }

    @Test
    public void givenIndianCensusData_WhenFileIsEmptyOrNoDataInList_ShouldReturnException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            String sortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData();
            IndiaCensusCSV[] CensusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExecptionType.NO_DATA, e.type);
        }
    }

    @Test
    public void givenUSCsvFile_WhenChecked_ReturnCorrectRecords() {
        try {
            CensusAnalyser censusAanlyser = new CensusAnalyser();
            int numberOfRecords = censusAanlyser.loadUSCensusData(US_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(51, numberOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenUSCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadUSCensusData(US_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.getPopulationWiseSortedUSCensusData();
        USCensusCSV[] CensusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
        Assert.assertEquals("0", CensusCSV[0].population);
    }

    @Test
    public void givenUSCensusData_WhenSortedOnPopulationDensity_ShouldReturnSortedResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadUSCensusData(US_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.getPopulationDensityWiseSortedUSCensusData();
        USCensusCSV[] CensusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
        Assert.assertEquals("0.46", CensusCSV[0].populationDensity);
    }

    @Test
    public void givenUSCensusData_WhenSortedOnArea_ShouldReturnSortedResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadUSCensusData(US_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.getAreaWiseSortedUSCensusData();
        USCensusCSV[] CensusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
        Assert.assertEquals("102269.23", CensusCSV[50].area);
    }

    @Test
    public void givenUSAndIndiaCensus_WhenSorted_ShouldReturnName() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        CensusAnalyser censusAnalyser1 = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        censusAnalyser1.loadUSCensusData(US_CENSUS_CSV_FILE_PATH);
        String sortedIndianData = censusAnalyser.getPopulationWiseSortedCensusData();
        String sortedUSData = censusAnalyser1.getStateWiseSortedCensusData();
        USCensusCSV[] CensusUsCSV = new Gson().fromJson(sortedUSData, USCensusCSV[].class);
        IndiaCensusCSV[] CensusCSV = new Gson().fromJson(sortedIndianData, IndiaCensusCSV[].class);
        Assert.assertEquals("Wyoming", CensusUsCSV[CensusUsCSV.length - 1].state);
        Assert.assertEquals("Uttar Pradesh", CensusCSV[CensusCSV.length - 1].state);

    }
}

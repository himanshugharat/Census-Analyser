package com.bridglabz;

import customcsv.util.CSVBuilderException;
import customcsv.util.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class CensusLoader {

    public <E> Map loadCensusData(String csvFilePath, Class<E> CensusCSVClass) throws CensusAnalyserException {
        HashMap<String, CensusDAO> map = new HashMap<>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvFileIterator = csvBuilder.getCSVFileIterator(reader, CensusCSVClass);
            Iterable<E> censusCSVIterable = () -> csvFileIterator;
            if (CensusCSVClass.getName().equals("com.bridglabz.IndiaCensusCSV")) {
                StreamSupport.stream(censusCSVIterable.spliterator(), false)
                        .map(IndiaCensusCSV.class::cast)
                        .forEach(csvState -> map.put(csvState.state, new CensusDAO(csvState)));
            } else if (CensusCSVClass.getName().equals("com.bridglabz.USCensusCSV")) {
                StreamSupport.stream(censusCSVIterable.spliterator(), false)
                        .map(USCensusCSV.class::cast)
                        .forEach(csvState -> map.put(csvState.state, new CensusDAO(csvState)));
            }
            return map;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExecptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExecptionType.FILE_TYPE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    e.type.name());
        }
    }
}

package com.bridglabz;

import com.google.gson.Gson;
import customcsv.util.CSVBuilderException;
import customcsv.util.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    Map<String, CensusDAO> map = new HashMap<>();

    public int loadIndiaCensusData(String... csvFilePath) throws CensusAnalyserException {
        map = new CensusLoader().loadCensusData(IndiaCensusCSV.class, csvFilePath);
        return map.size();
    }

    public int loadUSCensusData(String csvFilePath) throws CensusAnalyserException {
        map = new CensusLoader().loadCensusData(USCensusCSV.class, csvFilePath);
        return map.size();
    }



    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        if (map == null || map.size() == 0) {
            throw new CensusAnalyserException("no data ", CensusAnalyserException.ExecptionType.NO_DATA);
        }
        Comparator<CensusDAO> censusCSVComparator = Comparator.comparing((census -> census.state));
        List<CensusDAO> censusDAOS = map.values().stream().collect(Collectors.toList());
        Collections.sort(censusDAOS, censusCSVComparator);
        String sortedStateJsonCensus = new Gson().toJson(censusDAOS);
        return sortedStateJsonCensus;
    }

    public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
        if (map == null || map.size() == 0) {
            throw new CensusAnalyserException("no data ", CensusAnalyserException.ExecptionType.NO_DATA);
        }
        Comparator<CensusDAO> censusCSVComparator = Comparator.comparing((census -> census.population));
        List<CensusDAO> censusDAOS = map.values().stream().collect(Collectors.toList());
        Collections.sort(censusDAOS, censusCSVComparator);
        String sortedStateJsonCensus = new Gson().toJson(censusDAOS);
        return sortedStateJsonCensus;
    }

    public String getDensityWiseSortedCensusData() throws CensusAnalyserException {
        if (map == null || map.size() == 0) {
            throw new CensusAnalyserException("no data ", CensusAnalyserException.ExecptionType.NO_DATA);
        }
        Comparator<CensusDAO> censusCSVComparator = Comparator.comparing((census -> census.densityPerSqKm));
        List<CensusDAO> censusDAOS = map.values().stream().collect(Collectors.toList());
        Collections.sort(censusDAOS, censusCSVComparator);
        String sortedStateJsonCensus = new Gson().toJson(censusDAOS);
        return sortedStateJsonCensus;
    }

    public String getAreaWiseSortedCensusData() throws CensusAnalyserException {
        if (map == null || map.size() == 0) {
            throw new CensusAnalyserException("no data ", CensusAnalyserException.ExecptionType.NO_DATA);
        }
        Comparator<CensusDAO> censusCSVComparator = Comparator.comparing((census -> census.areaInSqKm));
        List<CensusDAO> censusDAOS = map.values().stream().collect(Collectors.toList());
        Collections.sort(censusDAOS, censusCSVComparator);
        String sortedStateJsonCensus = new Gson().toJson(censusDAOS);
        return sortedStateJsonCensus;
    }

    public String getPopulationWiseSortedUSCensusData() throws CensusAnalyserException {
        if (map == null || map.size() == 0) {
            throw new CensusAnalyserException("no data ", CensusAnalyserException.ExecptionType.NO_DATA);
        }
        Comparator<CensusDAO> censusCSVComparator = Comparator.comparing((census -> census.usPopulation));
        List<CensusDAO> censusDAOS = map.values().stream().collect(Collectors.toList());
        Collections.sort(censusDAOS, censusCSVComparator);
        String sortedStateJsonCensus = new Gson().toJson(censusDAOS);
        return sortedStateJsonCensus;
    }

    public String getPopulationDensityWiseSortedUSCensusData() throws CensusAnalyserException {
        if (map == null || map.size() == 0) {
            throw new CensusAnalyserException("no data ", CensusAnalyserException.ExecptionType.NO_DATA);
        }
        Comparator<CensusDAO> censusCSVComparator = Comparator.comparing((census -> census.populationDensity));
        List<CensusDAO> censusDAOS = map.values().stream().collect(Collectors.toList());
        Collections.sort(censusDAOS, censusCSVComparator);
        String sortedStateJsonCensus = new Gson().toJson(censusDAOS);
        return sortedStateJsonCensus;
    }

    public String getAreaWiseSortedUSCensusData() throws CensusAnalyserException {
        if (map == null || map.size() == 0) {
            throw new CensusAnalyserException("no data ", CensusAnalyserException.ExecptionType.NO_DATA);
        }
        Comparator<CensusDAO> censusCSVComparator = Comparator.comparing((census -> census.totalArea));
        List<CensusDAO> censusDAOS = map.values().stream().collect(Collectors.toList());
        Collections.sort(censusDAOS, censusCSVComparator);
        String sortedStateJsonCensus = new Gson().toJson(censusDAOS);
        return sortedStateJsonCensus;
    }
}

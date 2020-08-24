package com.bridglabz;

import com.google.gson.Gson;
import customcsv.util.CSVBuilderException;
import customcsv.util.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    HashMap<String, CensusDAO> map = new HashMap<>();
    List<IndiaCensusDAO> censusCSVList = null;
    List<IndiaStateCodeDAO> censusStateCSVList = null;
    List<USCensusDAO> usCensusCSVList = null;
    static ArrayList stateList;
    static ArrayList censusList;
    static ArrayList usList;


    public CensusAnalyser() {
        this.censusCSVList = new ArrayList<IndiaCensusDAO>();
        this.censusStateCSVList = new ArrayList<IndiaStateCodeDAO>();
        this.usCensusCSVList = new ArrayList<USCensusDAO>();
    }

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        return this.loadCensusData(csvFilePath, IndiaCensusCSV.class);

    }

    private <E> int loadCensusData(String csvFilePath, Class<E> CensusCSVClass) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IndiaCensusCSV.class);
            Iterable<E> censusCSVIterable = () -> csvFileIterator;
            StreamSupport.stream(censusCSVIterable.spliterator(), false)
                    .map(IndiaCensusCSV.class::cast)
                    .forEach(csvState -> map.put(csvState.state, new CensusDAO(csvState)));

            return map.size();
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

    public int loadIndiaStateData(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaStateCodeCSV> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IndiaStateCodeCSV.class);
            Iterable<IndiaStateCodeCSV> censusCSVIterable = () -> csvFileIterator;
            StreamSupport.stream(censusCSVIterable.spliterator(), false)
                    .forEach(csvState -> map.put(csvState.stateCode, new CensusDAO(csvState)));
            return map.size();
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

    public int loadUSCensusData(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<USCensusCSV> csvFileIterator = csvBuilder.getCSVFileIterator(reader, USCensusCSV.class);
            Iterable<USCensusCSV> censusCSVIterable = () -> csvFileIterator;
            StreamSupport.stream(censusCSVIterable.spliterator(), false)
                    .forEach(csvState -> map.put(csvState.state, new CensusDAO(csvState)));
            return map.size();
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

    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> csvIterable = () -> iterator;
        return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
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

package com.bridglabz;

import com.google.gson.Gson;
import customcsv.util.CSVBuilderException;
import customcsv.util.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    HashMap<Class,List> map=new HashMap<>();
    List<IndiaCensusDAO> censusCSVList = null;
    List<IndiaStateCodeDAO> censusStateCSVList = null;
    static ArrayList stateList;
    static ArrayList censusList;


    public CensusAnalyser() {
        this.censusCSVList = new ArrayList<IndiaCensusDAO>();
        this.censusStateCSVList = new ArrayList<IndiaStateCodeDAO>();
    }

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaCensusCSV> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IndiaCensusCSV.class);
            Iterable<IndiaCensusCSV> censusCSVIterable=() -> csvFileIterator;
            StreamSupport.stream(censusCSVIterable.spliterator(),false)
                    .forEach(csvState -> censusCSVList.add(new IndiaCensusDAO(csvState)));
            map.put(IndiaCensusCSV.class,censusCSVList);
            censusList=new ArrayList(map.get(IndiaCensusCSV.class));
            return map.get(IndiaCensusCSV.class).size();
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
            Iterable<IndiaStateCodeCSV> censusCSVIterable=() -> csvFileIterator;
            StreamSupport.stream(censusCSVIterable.spliterator(),false)
                    .forEach(csvStateCode -> censusStateCSVList.add(new IndiaStateCodeDAO(csvStateCode)));
            map.put(IndiaStateCodeCSV.class,censusStateCSVList);
            stateList=new ArrayList(map.get(IndiaStateCodeCSV.class));
            return censusStateCSVList.size();
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
        if (censusCSVList == null || censusCSVList.size() == 0) {
            throw new CensusAnalyserException("no data ", CensusAnalyserException.ExecptionType.NO_DATA);
        }
        Comparator<IndiaCensusDAO> censusCSVComparator = Comparator.comparing((census -> census.state));
        Collections.sort(censusCSVList, censusCSVComparator);
        String sortedStateJsonCensus = new Gson().toJson(censusCSVList);
        return sortedStateJsonCensus;
    }

    public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
        if (censusCSVList == null || censusCSVList.size() == 0) {
            throw new CensusAnalyserException("no data ", CensusAnalyserException.ExecptionType.NO_DATA);
        }
        Comparator<IndiaCensusDAO> censusCSVComparator = Comparator.comparing((census -> census.population));
        Collections.sort(censusCSVList, censusCSVComparator);
        String sortedStateJsonCensus = new Gson().toJson(censusCSVList);
        return sortedStateJsonCensus;
    }

    public String getDensityWiseSortedCensusData() throws CensusAnalyserException {
        if (censusCSVList == null || censusCSVList.size() == 0) {
            throw new CensusAnalyserException("no data ", CensusAnalyserException.ExecptionType.NO_DATA);
        }
        Comparator<IndiaCensusDAO> censusCSVComparator = Comparator.comparing((census -> census.densityPerSqKm));
        Collections.sort(censusCSVList, censusCSVComparator);
        String sortedStateJsonCensus = new Gson().toJson(this.censusCSVList);
        return sortedStateJsonCensus;
    }

    public String getAreaWiseSortedCensusData() throws CensusAnalyserException {
        if (censusCSVList == null || censusCSVList.size() == 0) {
            throw new CensusAnalyserException("no data ", CensusAnalyserException.ExecptionType.NO_DATA);
        }
        Comparator<IndiaCensusDAO> censusCSVComparator = Comparator.comparing((census -> census.area));
        Collections.sort(censusCSVList, censusCSVComparator);
        String sortedStateJsonCensus = new Gson().toJson(censusCSVList);
        return sortedStateJsonCensus;
    }
}

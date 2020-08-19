package com.bridglabz;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            Iterator<IndiaStateCodeCSV> censusCSVIterator = this.getCSVFileIterator(reader, IndiaCensusCSV.class);
            Iterable<IndiaStateCodeCSV> csvIterable = () -> censusCSVIterator;
            return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExecptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExecptionType.FILE_TYPE_PROBLEM);
        }
    }

    public int loadIndiaStateData(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            Iterator<IndiaStateCodeCSV> stateCodeCSVIterator = this.getCSVFileIterator(reader, IndiaStateCodeCSV.class);

            Iterable<IndiaStateCodeCSV> csvIterable = () -> stateCodeCSVIterator;
            return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExecptionType.CENSUS_FILE_PROBLEM);
        }
    }

    private <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CensusAnalyserException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<E> csvToBean = csvToBeanBuilder.build();
            return csvToBean.iterator();
        } catch (IllegalStateException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExecptionType.FILE_HEADER_OR_DELIMITER_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExecptionType.FILE_TYPE_PROBLEM);
        }
    }

}

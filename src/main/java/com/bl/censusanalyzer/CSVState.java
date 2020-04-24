package com.bl.censusanalyzer;
import com.bl.censusanalyser.exception.StateCensusAnalyserException;
import com.bl.model.CSVStateCode;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.Iterator;

import static java.nio.file.Files.newBufferedReader;

//import com.censusanalyser.CSVState;

public class CSVState
{
    public static String STATE_CODE_FILE = "./src/test/resources/StateCode.csv";

    public static void main(String args[]) throws IOException, StateCensusAnalyserException {
        CSVState csvState = new CSVState();
        csvState.loadStateCodes(STATE_CODE_FILE);
    }
    public int loadStateCodes(String FILE_PATH) throws IOException, StateCensusAnalyserException {
        int totalRecords = 0;
        try (Reader reader = newBufferedReader(Paths.get(FILE_PATH))) {
            CsvToBean<CSVStateCode> csvStateCodeBeanObject = new CsvToBeanBuilder(reader)
                    .withType(CSVStateCode.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<CSVStateCode> csvStateCodePojoIterator = csvStateCodeBeanObject.iterator();
            while (csvStateCodePojoIterator.hasNext()) {
                CSVStateCode csvStateCensus = csvStateCodePojoIterator.next();
                totalRecords++;
            }
        } catch (IOException e) {
            throw new StateCensusAnalyserException(e.getMessage(),StateCensusAnalyserException.exceptionType.FILE_NOT_FOUND);
        }
        return totalRecords;
    }
}


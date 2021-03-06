/***********************************************************************************************************************
 * @purpose:Load data from csv file and sort Data according to field value
 * @Author:priyanka garad
 ***********************************************************************************************************************/
package com.bl.censusanalyzer;
import com.bl.builder.CSVBuilderFactory;
import com.bl.builder.ICSVBuilder;
import com.bl.censusanalyser.exception.CSVBuilderException;
import com.bl.censusanalyser.exception.StateCensusAnalyserException;
import com.bl.dao.CSVStateCensusDAO;
import com.bl.model.CSVStateCensus;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class StateCensusAnalyser<T> {
    List<T> csvFileList = null;
    Map<Object, T> csvStateCodeMap = new HashMap<>();

    /**
     * @purpose:load data from Csv File and Store the Record in list and calculated count of number of record
     * @param csvFilePath:path of Csv file that define file fileUtility class
     * @param csvClass
     * @return:number of records
     * @throws CSVBuilderException
     */
    public int loadIndianData(String csvFilePath, Class<T> csvClass) throws CSVBuilderException {
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder icsvBuilder = CSVBuilderFactory.icsBuilder();
            Iterator<T> csvStateCensusIterator = icsvBuilder.getFileIterator(reader, csvClass);
            while (csvStateCensusIterator.hasNext()) {
                CSVStateCensusDAO value = new CSVStateCensusDAO((CSVStateCensus) csvStateCensusIterator.next());
                this.csvStateCodeMap.put(value.getState(), (T) value);
                csvFileList = csvStateCodeMap.values().stream().collect(Collectors.toList());
            }
            int totalRecords = csvStateCodeMap.size();
            return totalRecords;
        } catch (IOException e) {
            throw new StateCensusAnalyserException(e.getMessage(), StateCensusAnalyserException.exceptionType.FILE_NOT_FOUND);
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(e.getMessage(), StateCensusAnalyserException.exceptionType.INCORRECT_FILE);
        }
    }


    public String getSortData(Object T,int Number) throws StateCensusAnalyserException {
        if (csvFileList.size() == 0 | csvFileList == null) {
            throw new StateCensusAnalyserException("No Census Data", StateCensusAnalyserException.exceptionType.NO_CENSUS_DATA);
        }
        this.sort(csvFileList, Number);
        String sortedData = new Gson().toJson(csvFileList);
        return sortedData;
    }

    /**
     * @purpose:sorted the record
     * @param csvFileList
     * @param number
     */
    public void sort(List<T> csvFileList,int number) {
        for (int i = 0; i < csvFileList.size(); i++) {
            for (int j = 0; j < csvFileList.size() - i - 1; j++) {
                String census1[] = csvFileList.get(i).toString().split(",");
                String census2[] =csvFileList.get(j).toString().split(",");
                if (census1[number].compareToIgnoreCase(census2[number])>0) {
                    T censusData = csvFileList.get(i);
                    T censusData1 = csvFileList.get(j);
                    csvFileList.set(j, censusData);
                    csvFileList.set(i,censusData1);
                }
            }
        }
    }
}

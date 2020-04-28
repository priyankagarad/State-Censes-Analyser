package com.bl.censusanalyzer;
import com.bl.censusanalyser.exception.CSVBuilderException;
import com.bl.censusanalyser.exception.StateCensusAnalyserException;
import com.bl.model.CSVStateCensus;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
public class StateCensusAnalyser<T>
{
    List<T> csvFileList=null;
    Map<Object,T> csvStateCodeMap = new HashMap<>();

    /* Read State Census Data CSV file */
    public int loadIndianData(String csvFilePath, Class<T> csvClass) throws CSVBuilderException
    {
        try
        {
            BufferedReader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder icsvBuilder=CSVBuilderFactory.icsBuilder();
            Iterator<T> csvStateCensusIterator = icsvBuilder.getFileIterator(reader, csvClass);
            Iterable<T> stateCensusIterable = () -> csvStateCensusIterator;
            while (csvStateCensusIterator.hasNext())
            {
                CSVStateCensusDAO value =new CSVStateCensusDAO((CSVStateCensus) csvStateCensusIterator.next());
                this.csvStateCodeMap.put(value.getState(), (T) value);
                csvFileList = csvStateCodeMap.values().stream().collect(Collectors.toList());
            }
            int totalRecords = csvStateCodeMap.size();
            return totalRecords;
        } catch (IOException e)
        {
            throw new StateCensusAnalyserException(e.getMessage(),StateCensusAnalyserException.exceptionType.FILE_NOT_FOUND);
        }
        catch (RuntimeException e)
        {
            throw new StateCensusAnalyserException(e.getMessage(),StateCensusAnalyserException.exceptionType.INCORRECT_FILE);
        }
    }
    /* Count The Number Of Record in Csv File */
    public  <E> int getCount(Iterator<E> iterator)
    {
        Iterable<E> iterable=() -> iterator;
        int totalRecords=(int) StreamSupport.stream(iterable.spliterator(),false).count();
        return totalRecords;
    }
    /* Sort The Data From Csv File */
    public String getSortData(Object T) throws StateCensusAnalyserException
    {
        if (csvFileList.size() == 0 | csvFileList == null)
        {
            throw new StateCensusAnalyserException("No Census Data",StateCensusAnalyserException.exceptionType.NO_CENSUS_DATA);
        }
        Comparator<T>stateCensusAnalyserComparator = Comparator.comparing(csvCounter->T.toString());
        this.sort(csvFileList,stateCensusAnalyserComparator);
        String sortedData =new Gson().toJson(csvFileList);
        return  sortedData;
    }
        public void sort(List<T> csvFileList,Comparator<T> censusComparator)
    {
        for (int i = 0; i < csvFileList.size(); i++)
        {
            for (int j = 0; j < csvFileList.size() - i - 1; j++)
            {
                T census1 = csvFileList.get(j);
                T census2 = csvFileList.get(j + 1);
                if (censusComparator.compare(census1, census2) > 0)
                {
                    csvFileList.set(j, census2);
                    csvFileList.set(j,census1);
                }
            }
        }
    }
}
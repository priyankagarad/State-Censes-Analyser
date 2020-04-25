package com.bl.censusanalyzer;
import com.bl.censusanalyser.exception.CSVBuilderException;
import com.bl.censusanalyser.exception.StateCensusAnalyserException;
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
    Map<String,T> csvStateCodeMap = new HashMap<>();

    /* Read State Census Data CSV file */
    public int loadIndianData(String csvFilePath,Object T) throws CSVBuilderException
    {
        try
        {
            BufferedReader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder icsvBuilder=CSVBuilderFactory.icsBuilder();
            Iterator<T> csvStateCensusIterator = (Iterator<T>) icsvBuilder.getFileIterator(reader,T.getClass());
            while (csvStateCensusIterator.hasNext())
            {
                T value = csvStateCensusIterator.next();
                this.csvStateCodeMap.put(T.toString(),value);
                csvFileList = csvStateCodeMap.values().stream().collect(Collectors.toList());
            }
            int totalRecords = csvStateCodeMap.size();
            return totalRecords;

        } catch (IOException e) {

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
        this.sort(stateCensusAnalyserComparator);
        String stateCensusSortedJson = new Gson().toJson(csvFileList);
        return stateCensusSortedJson;
    }

    public void sort(Comparator<T> censusComparator)
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
                    csvFileList.set(j + 1, census1);
                }
            }
        }
    }
}
package com.bl.censusanalyzer;
import com.bl.censusanalyser.exception.CSVBuilderException;
import com.bl.censusanalyser.exception.StateCensusAnalyserException;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;
public class StateCensusAnalyser<T>
{
    public int loadIndianData(String csvFilePath,Object T) throws CSVBuilderException {
        try
        {
            BufferedReader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder icsvBuilder=CSVBuilderFactory.icsBuilder();
            List<T> censusCsvlist= (List<T>) icsvBuilder.getFileIterator(reader,T.getClass());
            return censusCsvlist.size();
        }
        catch (IOException e)
        {
            throw new StateCensusAnalyserException(e.getMessage(),StateCensusAnalyserException.exceptionType.FILE_NOT_FOUND);
        }
        catch (RuntimeException e)
        {
            throw new StateCensusAnalyserException(e.getMessage(),StateCensusAnalyserException.exceptionType.INCORRECT_FILE);
        }
    }
    private <E> int getCount(Iterator<E> iterator)
    {
        Iterable<E> iterable=() -> iterator;
        int totalRecords=(int) StreamSupport.stream(iterable.spliterator(),false).count();
        return totalRecords;
    }
}
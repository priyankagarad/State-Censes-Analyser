package com.censusanalyser;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.Iterator;
import static java.nio.file.Files.newBufferedReader;

public class CSVStates
{
    public static String STATE_CODE_FILE = "./src/test/resources/StateCode.csv";

    public static void main(String args[]) throws IOException, StateCensusAnalyserException {
        CSVStates csvState = new CSVStates();
        csvState.loadStateCodes(STATE_CODE_FILE);
    }

    public int loadStateCodes(String FILE_PATH) throws IOException, StateCensusAnalyserException {
        int totalRecords = 0;
        try (Reader reader = newBufferedReader(Paths.get(FILE_PATH))) {
            CsvToBean<CSVStateCodePojo> csvStateCodeBeanObject = new CsvToBeanBuilder(reader)
                    .withType(CSVStateCodePojo.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<CSVStateCodePojo> csvStateCodePojoIterator = csvStateCodeBeanObject.iterator();
            while (csvStateCodePojoIterator.hasNext()) {
                 CSVStateCodePojo csvStateCensus = csvStateCodePojoIterator.next();
                totalRecords++;
            }
        }catch(IOException e)
        {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.exceptionType.FILE_NOT_FOUND);
        }
        catch (RuntimeException e)
        {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.exceptionType.INCORRECT_FILE);
        }
        return totalRecords;
    }
}




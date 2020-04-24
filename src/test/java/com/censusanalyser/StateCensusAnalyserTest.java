package com.censusanalyser;
import com.bl.censusanalyser.exception.StateCensusAnalyserException;
import com.bl.censusanalyzer.StateCensusAnalyser;
import com.bl.model.CSVStateCensus;
import com.bl.model.CSVStateCode;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import static com.bl.censusanalyzer.FilePath.*;
public class StateCensusAnalyserTest
{
    StateCensusAnalyser stateCensusAnalyser=new StateCensusAnalyser();

    /* TC 1.1 : Given the States Census CSV file, Check to ensure the Number of Record matches */
    @Test
    public void givenTheStatesCensusCSVFile_whenNumberOfRecordMatch_shouldReturnTrue()
    {
        try
        {
            int totalRecords = stateCensusAnalyser.loadIndianData(DATA_CSV_FILE_PATH, CSVStateCensus.class);
            Assert.assertEquals(29, totalRecords);
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

        /* TC 1.2 : Given the State Census CSV File if incorrect Returns a custom Exception */
    @Test
    public void givenFileName_whenImproper_shouldThrowException() throws IOException {
        try
        {
            stateCensusAnalyser.loadIndianData(IMPROPER_FILE_NAME,CSVStateCensus.class);
        }
        catch (StateCensusAnalyserException e)
        {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.FILE_NOT_FOUND, e.exceptionTypeObject);
        }
    }

    /* TC 1.3 : Given the State Census CSV File when correct but type incorrect Returns a custom Exception */
    @Test
    public void givenTheStateCensusCSVFile_whenCorrectButTypeIncorrect_shouldReturnCustomException() throws IOException
    {
        try
        {
            stateCensusAnalyser.loadIndianData(IMPROPER_FILE_TYPE,CSVStateCensus.class);
        }
        catch (StateCensusAnalyserException e)
        {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.FILE_NOT_FOUND, e.exceptionTypeObject);
        }
    }

    /* TC 1.4 : Given The state census File When Correct But Delimiter Incorrect Should Return Custom Exception */
    @Test
    public void givenTheStateCensusFile_WhenCorrectButDelimiterIncorrect_shouldReturnCustomException() throws IOException
    {
        try
        {
            stateCensusAnalyser.loadIndianData(WRONG_DELIMITER1,CSVStateCensus.class);
        }
        catch (StateCensusAnalyserException e)
        {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.INCORRECT_FILE,e.exceptionTypeObject);
        }
    }
    /* TC 1.5 : Given the State Census CSV File when correct but csv header incorrect Returns a custom Exception */
    @Test
    public void givenFileData_whenIncorrect_shouldThrowException() throws IOException {
        try
        {
            stateCensusAnalyser.loadIndianData(WRONG_FILE_FORMATE,CSVStateCensus.class);
        }
        catch (StateCensusAnalyserException e)
        {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.INCORRECT_FILE, e.exceptionTypeObject);
        }
    }
    /* TC 2.1 :Check to ensure the Number of Record matches */
    @Test
    public void givenTotalRecordsFromStateCsvDataFile_whenMatch_shouldReturnTrue() {
        try
        {
            int totalRecords = stateCensusAnalyser.loadIndianData(STATE_CODE_FILE, CSVStateCode.class);
            Assert.assertEquals(37, totalRecords);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

        /* TC 2.2 :test to check if StateCensusData file is incorrect */
    @Test
    public void givenStateCodeCSVFileName_whenImproper_shouldThrowException() throws IOException
    {
        try
        {
            stateCensusAnalyser.loadIndianData(IMPROPER_FILE_NAME, CSVStateCode.class);
        }
        catch (StateCensusAnalyserException e)
        {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.FILE_NOT_FOUND, e.exceptionTypeObject);
        }
    }

    /*TC 2.3: test to check if StateCensusData file is correct but type is incorrect */
    @Test
    public void givenStateCodeCSVFileType_whenIncorrect_shouldThrowException() throws IOException {
        try
        {
            stateCensusAnalyser.loadIndianData(IMPROPER_FILE_TYPE,CSVStateCode.class);
        }
        catch (StateCensusAnalyserException e)
        {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.FILE_NOT_FOUND,e.exceptionTypeObject);
        }
    }

    /*TC 2.4/2.5 test to check if StateCensus Data file is correct but Header and Delimiter is incorrect */
    @Test
    public void givenStateCodeCSVFileData_whenIncorrect_shouldThrowException() throws IOException {
        try
        {
            stateCensusAnalyser.loadIndianData(WRONG_STATE_CODE_FILE,CSVStateCode.class);
        }
        catch (StateCensusAnalyserException e)
        {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.INCORRECT_FILE, e.exceptionTypeObject);
        }
    }
}

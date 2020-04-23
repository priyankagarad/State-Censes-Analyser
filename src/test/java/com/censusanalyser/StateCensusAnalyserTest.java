package com.censusanalyser;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
public class StateCensusAnalyserTest
{
    private static String DATA_CSV_FILE_PATH = "./src/test/resources/StateCensusData.csv";
    private static String IMPROPER_FILE_NAME = "./src/test/resources/StateCensusData1.csv";
    private static String IMPROPER_FILE_TYPE = "./src/test/resources/StateCensusData.txt";
    private static String WRONG_DELIMITER1 = "./src/test/resources/DelimiterIncorrect.csv";
    private static String WRONG_FILE_FORMATE = "./src/test/resources/DelimiterIncorrect.csv";
    public static String STATE_CODE_FILE = "./src/test/resources/StateCode.csv";
    private static String WRONG_STATE_CODE_FILE = "./src/test/resources/StateCodeWrongFormat.csv";

    StateCensusAnalyser stateCensusAnalyser=new StateCensusAnalyser();

    /* TC 1.1 : Given the States Census CSV file, Check to ensure the Number of Record matches */
    @Test
    public void givenTheStatesCensusCSVFile_whenNumberOfRecordMatch_shouldReturnTrue() throws IOException, StateCensusAnalyserException {
        int totalRecords = stateCensusAnalyser.loadIndianCensusData(DATA_CSV_FILE_PATH);
        Assert.assertEquals(29, totalRecords);
    }

    /* TC 1.2 : Given the State Census CSV File if incorrect Returns a custom Exception */
    @Test
    public void givenFileName_whenImproper_shouldThrowException() throws IOException {
        try
        {
            stateCensusAnalyser.loadIndianCensusData(IMPROPER_FILE_NAME);
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
            stateCensusAnalyser.loadIndianCensusData(IMPROPER_FILE_TYPE);
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
            stateCensusAnalyser.loadIndianCensusData(WRONG_DELIMITER1);
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
            stateCensusAnalyser.loadIndianCensusData(WRONG_FILE_FORMATE);
        }
        catch (StateCensusAnalyserException e)
        {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.INCORRECT_FILE, e.exceptionTypeObject);
        }
    }
    /* TC 2.1 :Check to ensure the Number of Record matches */
    @Test
    public void givenTotalRecordsFromStateCsvDataFile_whenMatch_shouldReturnTrue() throws StateCensusAnalyserException, IOException
    {
        int totalRecords = stateCensusAnalyser.loadStateCodes(STATE_CODE_FILE);
        Assert.assertEquals(37, totalRecords);
    }

    /* TC 2.2 :test to check if StateCensusData file is incorrect */
    @Test
    public void givenStateCodeCSVFileName_whenImproper_shouldThrowException() throws IOException
    {
        try
        {
            stateCensusAnalyser.loadStateCodes(IMPROPER_FILE_NAME);
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
            stateCensusAnalyser.loadStateCodes(IMPROPER_FILE_TYPE);
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
            stateCensusAnalyser.loadStateCodes(WRONG_STATE_CODE_FILE);
        }
        catch (StateCensusAnalyserException e)
        {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.INCORRECT_FILE, e.exceptionTypeObject);
        }
    }
}

package com.censusanalyser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
public class StateCensusAnalyserTest
{
    private static String DATA_CSV_FILE_PATH = "./src/test/resources/StateCensusData.csv";
    private static String IMPROPER_FILE_NAME = "./src/test/resources/StateCensusData1.csv";
    private static String IMPROPER_FILE_TYPE = "./src/test/resources/StateCensusData.txt";
    private static String WRONG_DELIMITER1="./src/test/resources/DelimiterIncorrect.csv";

    /* TC 1.1 : Given the States Census CSV file, Check to ensure the Number of Record matches */
    @Test
    public void givenTheStatesCensusCSVFile_whenNumberOfRecordMatch_shouldReturnTrue() throws IOException, StateCensusAnalyserException
    {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(DATA_CSV_FILE_PATH);
        int totalRecords = stateCensusAnalyser.loadData();
        Assert.assertEquals(29, totalRecords);
    }
    /* TC 1.2 : Given the State Census CSV File if incorrect Returns a custom Exception */
    @Test
    public void givenFileName_whenImproper_shouldThrowException() throws IOException
    {
        try
        {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(DATA_CSV_FILE_PATH);
            stateCensusAnalyser = new StateCensusAnalyser(IMPROPER_FILE_NAME);
            stateCensusAnalyser.loadData();
        }
        catch (StateCensusAnalyserException e)
        {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.FILE_NOT_FOUND, e.exceptionTypeObject);
        }
    }
    /* TC 1.3 : Given the State Census CSV File when correct but type incorrect Returns a custom Exception */
    @Test
    public void givenTheStateCensusCSVFile_whenCorrectButTypeIncorrect_shouldReturnCustomException() throws IOException {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(IMPROPER_FILE_TYPE);
            stateCensusAnalyser.loadData();
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }
    // TC 1.4 : Given The state census File When Correct But Delimiter Incorrect Should Return Custom Exception
    @Test
    public void givenTheStateCensusFile_WhenCorrectButDelimiterIncorrect_shouldReturnCustomException() throws IOException
    {
        try
        {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser(WRONG_DELIMITER1);
            stateCensusAnalyser.loadData();
        }
        catch (StateCensusAnalyserException e)
        {
            e.printStackTrace();
        }
    }
}



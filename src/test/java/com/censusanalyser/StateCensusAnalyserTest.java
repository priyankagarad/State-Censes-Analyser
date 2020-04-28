package com.censusanalyser;
import com.bl.censusanalyser.exception.CSVBuilderException;
import com.bl.censusanalyser.exception.StateCensusAnalyserException;
import com.bl.censusanalyzer.CSVStateCensusDAO;
import com.bl.censusanalyzer.ConstantsForFileLocation;
import com.bl.censusanalyzer.StateCensusAnalyser;
import com.bl.model.CSVStateCensus;
import com.bl.model.CSVStateCode;
import com.bl.model.CSVUSCensusData;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static com.bl.censusanalyzer.ConstantsForFileLocation.*;
public class StateCensusAnalyserTest
{
    StateCensusAnalyser stateCensusAnalyser;
    ConstantsForFileLocation constant;

    @Before
    public void setUp() {
        stateCensusAnalyser = new StateCensusAnalyser();
    }
    /* TC 1.1 : Given the States Census CSV file, Check to ensure the Number of Record matches */
    @Test
    public void givenTheStatesCensusCSVFile_whenNumberOfRecordMatch_shouldReturnTrue() throws CSVBuilderException {
        try {
            int totalRecords = stateCensusAnalyser.loadIndianData(DATA_CSV_FILE_PATH, CSVStateCensus.class);
            Assert.assertEquals(29, totalRecords);
           /* int count = stateCensusAnalyser.loadIndianData(STATE_CODE_FILE, CSVStateCensus.class);
            Assert.assertEquals(37, count);*/
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }
    /* TC 1.2 : Given the State Census CSV File if incorrect Returns a custom Exception */
    @Test
    public void givenFileName_whenImproper_shouldThrowException() throws IOException, CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData(IMPROPER_FILE_NAME, CSVStateCensus.class);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.FILE_NOT_FOUND, e.exceptionTypeObject);
        }
    }
    /* TC 1.3 : Given the State Census CSV File when correct but type incorrect Returns a custom Exception */
    @Test
    public void givenTheStateCensusCSVFile_whenCorrectButTypeIncorrect_shouldReturnCustomException() throws IOException,
            CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData(IMPROPER_FILE_TYPE, CSVStateCensus.class);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.FILE_NOT_FOUND, e.exceptionTypeObject);
        }
    }
    /* TC 1.4 : Given The state census File When Correct But Delimiter Incorrect Should Return Custom Exception */
    @Test
    public void givenTheStateCensusFile_WhenCorrectButDelimiterIncorrect_shouldReturnCustomException() throws IOException, CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData(WRONG_DELIMITER1, CSVStateCensus.class);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.INCORRECT_FILE, e.exceptionTypeObject);
        }
    }
    /* TC 1.5 : Given the State Census CSV File when correct but csv header incorrect Returns a custom Exception */
    @Test
    public void givenFileData_whenIncorrect_shouldThrowException() throws IOException, CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData(WRONG_FILE_FORMATE, CSVStateCensus.class);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.INCORRECT_FILE, e.exceptionTypeObject);
        }
    }
    /* TC 2.1 :Check to ensure the Number of Record matches */
    @Test
    public void givenTotalRecordsFromStateCsvDataFile_whenMatch_shouldReturnTrue() throws CSVBuilderException {
        try {
            int totalRecords = stateCensusAnalyser.loadIndianData(STATE_CODE_FILE, CSVStateCode.class);
            Assert.assertEquals(37, totalRecords);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /* TC 2.2 :test to check if StateCensusData file is incorrect */
    @Test
    public void givenStateCodeCSVFileName_whenImproper_shouldThrowException() throws IOException, CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData(IMPROPER_FILE_NAME, CSVStateCode.class);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.FILE_NOT_FOUND, e.exceptionTypeObject);
        }
    }
    /*TC 2.3: test to check if StateCensusData file is correct but type is incorrect */
    @Test
    public void givenStateCodeCSVFileType_whenIncorrect_shouldThrowException() throws IOException, CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData(IMPROPER_FILE_TYPE, CSVStateCode.class);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.FILE_NOT_FOUND, e.exceptionTypeObject);
        }
    }
    /* TC 2.4/2.5 test to check if StateCensus Data file is correct but Header and Delimiter is incorrect */
    @Test
    public void givenStateCodeCSVFileData_whenIncorrect_shouldThrowException() throws IOException, CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData(WRONG_STATE_CODE_FILE, CSVStateCode.class);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.INCORRECT_FILE, e.exceptionTypeObject);
        }
    }
    /* TC : 3 test to check census data is sorted in Json format */
    @Test
    public void givenStateCensusData_whenSortedOnStates_shouldReturnSortedResult() throws CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData(DATA_CSV_FILE_PATH, CSVStateCensus.class);
            String sortedStateCensusData = stateCensusAnalyser.getSortData(CSVStateCensus.class);
            CSVStateCensus[] csvStateCensus = new Gson().fromJson(sortedStateCensusData, CSVStateCensus[].class);
           // Assert.assertEquals("Andhra Pradesh",csvStateCensus[0].getState());
            //Assert.assertEquals("West Bengal",csvStateCensus[0].getState());
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }
    /* TC : 4 test to check census data is sorted in Json format according to State code */
    @Test
    public void givenStateCodeData_whenSortedOnStates_shouldReturnSortedResult() throws CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData(STATE_CODE_FILE, CSVStateCode.class);
            String sortedStateCodeData = stateCensusAnalyser.getSortData(CSVStateCode.class);
            CSVStateCode[] csvStateCodePojo = new Gson().fromJson(sortedStateCodeData, CSVStateCode[].class);
            Assert.assertEquals("AD", csvStateCodePojo[1].getStateCode());
            Assert.assertEquals("WB", csvStateCodePojo[37].getStateCode());
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }
    /* TC : 5 test to check census data is sorted in Json format according to Population name */
    @Test
    public void givenStateCensusPopulationData_whenSortedOnStates_shouldReturnSortedResult() throws CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData("STATE_CODE_FILE", CSVStateCensus.class);
            String sortedStateCensusData = stateCensusAnalyser.getSortData(CSVStateCensusDAO.class);
            CSVStateCensus[] csvStateCensus = new Gson().fromJson(sortedStateCensusData, CSVStateCensus[].class);
            Assert.assertEquals(199812341, csvStateCensus[0].getPopulation());
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }
   /* TC 6 : test to check census data is sorted in Json format according to Density */
    @Test
    public void givenStateCensusDensityWiseData_whenSortedOnStates_shouldReturnSortedResult() throws CSVBuilderException
    {
        try {
            stateCensusAnalyser.loadIndianData(DATA_CSV_FILE_PATH,CSVStateCensus.class);
            String sortedStateCensusData = stateCensusAnalyser.getSortData(DATA_CSV_FILE_PATH);
            System.out.println(sortedStateCensusData);
            CSVStateCensus[] csvStateCensus = new Gson().fromJson(sortedStateCensusData,CSVStateCensus[].class);
            Assert.assertEquals(1102, csvStateCensus[0].getDensityPerSqKm());
           } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }
    /* TC : 7 test to check census data is sorted in Json format according to Area Wise */
    @Test
    public void givenStateCensusData_whenSortedOnAreaWise_shouldReturnSortedResult() throws CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData(DATA_CSV_FILE_PATH, CSVStateCensus.class);
            String sortedStateCensusData = stateCensusAnalyser.getSortData(CSVStateCensusDAO.class);
            System.out.println(sortedStateCensusData);
            CSVStateCensus[] csvStateCensusPojo = new Gson().fromJson(sortedStateCensusData, CSVStateCensus[].class);
            Assert.assertEquals(94163, csvStateCensusPojo[0].getAreaInSqKm());
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }
    //TC: 8 Test Ensure Number Of Records Matches of USCensusData
    @Test
    public void givenTotalRecordFromCSV_WhenMatched_ShouldReturnTrue() throws CSVBuilderException
    {
        try {
            int totalRecords=stateCensusAnalyser.loadIndianData(US_CSV_FILE_PATH, CSVUSCensusData.class);
            System.out.println(totalRecords);
            Assert.assertEquals(51,totalRecords);
        }catch (StateCensusAnalyserException e)
        {

        }
    }
}

package com.censusanalyser;
import com.bl.censusanalyser.exception.CSVBuilderException;
import com.bl.censusanalyser.exception.StateCensusAnalyserException;
import com.bl.censusanalyzer.StateCensusAnalyser;
import com.bl.dao.CSVStateCensusDAO;
import com.bl.model.CSVStateCensus;
import com.bl.model.CSVStateCode;
import com.bl.model.CSVUSCensusData;
import org.junit.Test;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import java.io.IOException;
import static com.bl.censusanalyzer.FileUtility.*;
public class StateCensusAnalyserTest
{
    StateCensusAnalyser stateCensusAnalyser;
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
        } catch (StateCensusAnalyserException e) {
        }
    }

    /* TC 1.2 : Given the State Census CSV File if incorrect Returns a custom Exception */
    /* TC 1.3 : Given the State Census CSV File when correct but type incorrect Returns a custom Exception */
    @Test
    public void givenFileNameAndType_whenImproper_shouldThrowException() throws IOException, CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData(IMPROPER_FILE_NAME, CSVStateCensus.class);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.FILE_NOT_FOUND, e.exceptionTypeObject);
        }
    }

    /* TC 1.4 : Given The state census File When Correct But Delimiter Incorrect Should Return Custom Exception */
    /* TC 1.5 : Given the State Census CSV File when correct but csv header incorrect Returns a custom Exception */
    @Test
    public void givenTheStateCensusFile_WhenCorrectButDelimiterAndHeaderIncorrect_shouldReturnCustomException() throws IOException,
            CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData(WRONG_DELIMITER_AND_HEADER, CSVStateCensus.class);
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
        }
    }

    /* TC 2.2 :test to check if StateCensusData file is incorrect */
    /*TC 2.3: test to check if StateCensusData file is correct but type is incorrect */
    @Test
    public void givenStateCodeCSVFileNameType_whenImproper_shouldThrowException() throws IOException, CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData(IMPROPER_FILE_NAME, CSVStateCode.class);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.FILE_NOT_FOUND, e.exceptionTypeObject);
        }
    }

    /* TC 2.4/2.05 test to check if StateCensus Data file is correct but Header and Delimiter is incorrect */
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
            String sortedStateCensusData = stateCensusAnalyser.getSortData(CSVStateCensus.class, 0);
            CSVStateCensusDAO[] csvStateCensus = new Gson().fromJson(sortedStateCensusData, CSVStateCensusDAO[].class);
            Assert.assertEquals("Bihar", csvStateCensus[0].getState());
        } catch (StateCensusAnalyserException e) {
        }
    }

    /* TC : 4 test to check census data is sorted in Json format according to State code */
    @Test
    public void givenStateCodeData_whenSortedOnStates_shouldReturnSortedResult() throws CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData(STATE_CODE_FILE, CSVStateCode.class);
            String sortedStateCodeData = stateCensusAnalyser.getSortData(CSVStateCode.class, 4);
            CSVStateCensusDAO[] csvStateCodePojo = new Gson().fromJson(sortedStateCodeData, CSVStateCensusDAO[].class);
            Assert.assertEquals("AD", csvStateCodePojo[1].getStateCode());
            Assert.assertEquals("WB", csvStateCodePojo[37].getStateCode());
        } catch (StateCensusAnalyserException e) {
        }
    }

    /* TC : 5 test to check census data is sorted in Json format according to Population  */
    @Test
    public void givenStateCensusPopulationData_whenSortedOnStates_shouldReturnSortedResult() throws CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData("STATE_CODE_FILE", CSVStateCensus.class);
            String sortedStateCensusData = stateCensusAnalyser.getSortData(CSVStateCensusDAO.class, 1);
            CSVStateCensus[] csvStateCensus = new Gson().fromJson(sortedStateCensusData, CSVStateCensus[].class);
            Assert.assertEquals(199812341, csvStateCensus[0].getPopulation());
        } catch (StateCensusAnalyserException e) {
        }
    }

    /* TC 6 : test to check census data is sorted in Json format according to Density */
    @Test
    public void givenStateCensusDensityWiseData_whenSortedOnStates_shouldReturnSortedResult() throws CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData(DATA_CSV_FILE_PATH, CSVStateCode.class);
            String sortedStateCensusData = stateCensusAnalyser.getSortData(DATA_CSV_FILE_PATH, 3);
            CSVStateCensusDAO[] csvStateCensus = new Gson().fromJson(sortedStateCensusData, CSVStateCensusDAO[].class);
            Assert.assertEquals(1029, csvStateCensus[0].getDensityPerSqKm());
        } catch (StateCensusAnalyserException e) {
        }
    }

    /* TC : 7 test to check census data is sorted in Json format according to Area Wise */
    @Test
    public void givenStateCensusData_whenSortedOnAreaWise_shouldReturnSortedResult() throws CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData(DATA_CSV_FILE_PATH, CSVStateCensus.class);
            String sortedStateCensusData = stateCensusAnalyser.getSortData(CSVStateCensusDAO.class, 6);
            CSVStateCensusDAO[] csvStateCensusPojo = new Gson().fromJson(sortedStateCensusData,CSVStateCensusDAO[].class);
            Assert.assertEquals(94163, csvStateCensusPojo[0].getAreaInSqKm());
        } catch (StateCensusAnalyserException e) {
        }
    }

    //TC: 8 Test Ensure Number Of Records Matches of USCensusData
    @Test
    public void givenTotalRecordFromCSV_WhenMatched_ShouldReturnTrue() throws CSVBuilderException {
        try {
            int totalRecords = stateCensusAnalyser.loadIndianData(US_CSV_FILE_PATH, CSVUSCensusData.class);
            Assert.assertEquals(51, totalRecords);
        } catch (StateCensusAnalyserException e) {
        }
    }

    /*TC 9: Given State Census data when sorted should return sorted start population state */
    @Test
    public void givenCensusCSVData_WhenSorted_ThenReturnSortedStartPopulationState() throws CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData(US_CSV_FILE_PATH,CSVUSCensusData.class);
            String sortedStateCensusData = stateCensusAnalyser.getSortData(CSVStateCensus.class, 6);
            CSVStateCensusDAO[] csvStateCensus = new Gson().fromJson(sortedStateCensusData, CSVStateCensusDAO[].class);
            Assert.assertEquals("Wyoming", csvStateCensus[0].getState());
        } catch (StateCensusAnalyserException e) {
        }
    }

    /* TC :10.1 test to check US census data is sorted in Json format according to Density Wise */
    @Test
    public void givenUSStateCensusData_whenSortedOnDensityWise_shouldReturnSortedResult() throws CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData(US_CSV_FILE_PATH, CSVUSCensusData.class);
            String sortedStateCensusData = stateCensusAnalyser.getSortData(CSVUSCensusData.class, 5);
            CSVStateCensusDAO[] csvStateCensusPojo = new Gson().fromJson(sortedStateCensusData,CSVStateCensusDAO[].class);
            Assert.assertEquals(3805.61, csvStateCensusPojo[0].getPopulationDensity());
        } catch (StateCensusAnalyserException e) {
        }
    }

    /* TC:10.2 test to check US census data is sorted in Json format according to Area Wise */
    @Test
    public void givenUSStateCensusData_whenSortedOnAreaWise_shouldReturnSortedResult() throws CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData(US_CSV_FILE_PATH, CSVUSCensusData.class);
            String sortedStateCensusData = stateCensusAnalyser.getSortData(CSVUSCensusData.class, 6);
            CSVStateCensusDAO[] csvStateCensusPojo = new Gson().fromJson(sortedStateCensusData, CSVStateCensusDAO[].class);
            Assert.assertEquals(1723338, csvStateCensusPojo[0].getArea());
        } catch (StateCensusAnalyserException e) {
        }
    }

    /* Dual Sorting for US and india */
    @Test
    public void givenUsAndIndiaCensusData_WhenSorted_ThenShouldReturnSortedResult() throws CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData(DATA_CSV_FILE_PATH,CSVStateCensus.class);
            String sortedData = stateCensusAnalyser.getSortData(CSVStateCensus.class,1);
            CSVStateCensusDAO[] indianStateCensesAnalyzers = new Gson().fromJson(sortedData,CSVStateCensusDAO[].class);
            Assert.assertEquals(1980602, indianStateCensesAnalyzers[28].getPopulation());

            stateCensusAnalyser.loadIndianData(US_CSV_FILE_PATH,CSVUSCensusData.class);
            String UssortedData = stateCensusAnalyser.getSortData(CSVUSCensusData.class,5);
            CSVStateCensusDAO[] USStateCensesAnalyzers = new Gson().fromJson(UssortedData, CSVStateCensusDAO[].class);
            Assert.assertEquals(3805, USStateCensesAnalyzers[50].getPopulationDensity()); }
        catch (StateCensusAnalyserException e) {
        }
    }
}









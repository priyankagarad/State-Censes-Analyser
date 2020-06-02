package com.censusanalyser;
import com.bl.censusanalyser.exception.CSVBuilderException;
import com.bl.censusanalyser.exception.StateCensusAnalyserException;
import com.bl.censusanalyzer.StateCensusAnalyser;
import com.bl.dao.CSVStateCensusDAO;
import com.bl.model.CSVStateCensus;
import com.bl.model.CSVStateCode;
import com.bl.model.CSVUSCensusData;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static com.bl.censusanalyzer.FileUtility.*;
public class StateCensusAnalyserTest
{
    StateCensusAnalyser stateCensusAnalyser;

    @Before
    public void setUp() {
        stateCensusAnalyser = new StateCensusAnalyser();
    }

    @Test
    public void givenTheStatesCensusCSVFile_whenNumberOfRecordMatch_shouldReturnTrue() throws CSVBuilderException {
        try {
            int totalRecords = stateCensusAnalyser.loadIndianData(DATA_CSV_FILE_PATH, CSVStateCensus.class);
            Assert.assertEquals(29, totalRecords);
        } catch (StateCensusAnalyserException e) {
        }
    }


    @Test
    public void givenFileNameAndType_whenImproper_shouldThrowException() throws IOException, CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData(IMPROPER_FILE_NAME, CSVStateCensus.class);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.FILE_NOT_FOUND, e.exceptionTypeObject);
        }
    }

    @Test
    public void givenTheStateCensusFile_WhenCorrectButDelimiterAndHeaderIncorrect_shouldReturnCustomException() throws IOException,
            CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData(WRONG_DELIMITER_AND_HEADER, CSVStateCensus.class);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.INCORRECT_FILE, e.exceptionTypeObject);
        }
    }

    @Test
    public void givenTotalRecordsFromStateCsvDataFile_whenMatch_shouldReturnTrue() throws CSVBuilderException {
        try {
            int totalRecords = stateCensusAnalyser.loadIndianData(STATE_CODE_FILE, CSVStateCode.class);
            Assert.assertEquals(37, totalRecords);
        } catch (Exception e) {
        }
    }

    @Test
    public void givenStateCodeCSVFileNameType_whenImproper_shouldThrowException() throws IOException, CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData(IMPROPER_FILE_NAME, CSVStateCode.class);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.FILE_NOT_FOUND, e.exceptionTypeObject);
        }
    }

    @Test
    public void givenStateCodeCSVFileData_whenIncorrect_shouldThrowException() throws IOException, CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData(WRONG_STATE_CODE_FILE, CSVStateCode.class);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.exceptionType.INCORRECT_FILE, e.exceptionTypeObject);
        }
    }

    @Test
    public void givenStateCensusData_whenSortedOnStates_shouldReturnSortedResult() throws CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData(DATA_CSV_FILE_PATH, CSVStateCensus.class);
            String sortedStateCensusData = stateCensusAnalyser.getSortData(CSVStateCensus.class, 0);
            System.out.println(sortedStateCensusData);
            CSVStateCensusDAO[] csvStateCensus = new Gson().fromJson(sortedStateCensusData, CSVStateCensusDAO[].class);
            Assert.assertEquals("Andhra Pradesh", csvStateCensus[0].getState());
        } catch (StateCensusAnalyserException e) {
        }
    }

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

    @Test
    public void givenTotalRecordFromCSV_WhenMatched_ShouldReturnTrue() throws CSVBuilderException {
        try {
            int totalRecords = stateCensusAnalyser.loadIndianData(US_CSV_FILE_PATH, CSVUSCensusData.class);
            Assert.assertEquals(51, totalRecords);
        } catch (StateCensusAnalyserException e) {
        }
    }

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

    @Test
    public void givenUSStateCensusData_whenSortedOnDensityWise_shouldReturnSortedResult() throws CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData(US_CSV_FILE_PATH, CSVUSCensusData.class);
            String sortedStateCensusData = stateCensusAnalyser.getSortData(CSVUSCensusData.class, 5);
            CSVStateCensusDAO[] csvStateCensusPojo = new Gson().fromJson(sortedStateCensusData, CSVStateCensusDAO[].class);
            Assert.assertEquals(3805.61, csvStateCensusPojo[0].getPopulationDensity());
        } catch (StateCensusAnalyserException e) {
        }
    }

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

    @Test
    public void givenUsAndIndiaCensusData_WhenSorted_ThenShouldReturnSortedResult() throws CSVBuilderException {
        try {
            stateCensusAnalyser.loadIndianData(DATA_CSV_FILE_PATH,CSVStateCensus.class);
            String sortedData = stateCensusAnalyser.getSortData(CSVStateCensus.class,1);
            CSVStateCensusDAO[] indianStateCensesAnalyzers = new Gson().fromJson(sortedData,CSVStateCensusDAO[].class);
            Assert.assertEquals(91347736, indianStateCensesAnalyzers[0].getPopulation());

            stateCensusAnalyser.loadIndianData(US_CSV_FILE_PATH,CSVUSCensusData.class);
            String UssortedData = stateCensusAnalyser.getSortData(CSVUSCensusData.class,5);
            CSVStateCensusDAO[] USStateCensesAnalyzers = new Gson().fromJson(UssortedData, CSVStateCensusDAO[].class);
            Assert.assertEquals(3805, USStateCensesAnalyzers[0].getPopulationDensity()); }
        catch (StateCensusAnalyserException e) {
        }
    }
}









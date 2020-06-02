package com.censusanalyser;
import com.bl.censusanalyser.exception.CSVBuilderException;
import com.bl.censusanalyzer.StateCensusAnalyser;
import com.bl.model.CSVStateCensus;
import com.bl.model.CSVStateCode;
import com.bl.model.CSVUSCensusData;
import org.junit.Assert;
import org.junit.Test;
import static com.bl.censusanalyzer.FileUtility.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class StateCensusAnalyserUnitTest {
    StateCensusAnalyser stateCensusAnalyser = mock(StateCensusAnalyser.class);

    @Test
    public void givenTheStatesCensusCSVFile_whenNumberOfRecordMatch_shouldReturnCountOfRecord() throws CSVBuilderException {
        stateCensusAnalyser = mock(StateCensusAnalyser.class);
        when(stateCensusAnalyser.loadIndianData(DATA_CSV_FILE_PATH, CSVStateCensus.class)).thenReturn(29);
        int numberOfRecords = stateCensusAnalyser.loadIndianData(DATA_CSV_FILE_PATH, CSVStateCensus.class);
        Assert.assertEquals(29, numberOfRecords);
    }

    @Test
    public void givenTheStateCodeCSVFile_WhenNumberOfRecordsMatch_shouldReturnCountOfRecordRecords() throws CSVBuilderException {
        StateCensusAnalyser stateCensusAnalyser = mock(StateCensusAnalyser.class);
        when(stateCensusAnalyser.loadIndianData(STATE_CODE_FILE, CSVStateCode.class)).thenReturn(37);
        int numberOfRecords = stateCensusAnalyser.loadIndianData(STATE_CODE_FILE, CSVStateCode.class);
        Assert.assertEquals(37, numberOfRecords);
    }

   @Test
    public void givenTotalRecordFromCSV_WhenMatch_shouldReturnCountORecord() throws CSVBuilderException {
        StateCensusAnalyser stateCensusAnalyser = mock(StateCensusAnalyser.class);
        when(stateCensusAnalyser.loadIndianData(US_CSV_FILE_PATH, CSVUSCensusData.class)).thenReturn(51);
        int numberOfRecords=(stateCensusAnalyser.loadIndianData(US_CSV_FILE_PATH, CSVUSCensusData.class));
        Assert.assertEquals(51, numberOfRecords);
    }
}


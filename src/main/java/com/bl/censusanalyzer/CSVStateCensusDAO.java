package com.bl.censusanalyzer;
import com.bl.model.CSVStateCensus;
import com.bl.model.CSVStateCode;
public class CSVStateCensusDAO
{
    public String state;
    public int densityPerSqKm;
    public int areaInSqKm;
    public int population;
    public String stateCode;

    public CSVStateCensusDAO(CSVStateCensus indiaCensusCSV)
    {
        densityPerSqKm = indiaCensusCSV.getDensityPerSqKm();
        areaInSqKm = indiaCensusCSV.getAreaInSqKm();
        population = indiaCensusCSV.getPopulation();
        state = indiaCensusCSV.getState();
    }
    public CSVStateCensusDAO(CSVStateCode indiaCensusCSV)
    {
        stateCode = indiaCensusCSV.getStateCode();
    }
}
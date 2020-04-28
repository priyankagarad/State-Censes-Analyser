package com.bl.model;
import com.opencsv.bean.CsvBindByName;
public class CSVUSCensusData
{
    @CsvBindByName(column = "State Id")
    private String StateID;

    @CsvBindByName(column = "State")
    private String State;

    @CsvBindByName(column = "Population Density")
    private String PopulationDensity;

    @CsvBindByName(column = "Population")
    private String Population;

    @CsvBindByName(column = "Total area")
    private String Area;

    @CsvBindByName(column = "Housing units")
    private String HousingUnits;

    @CsvBindByName(column = "Water area")
    private String WaterArea;

    @CsvBindByName(column = "Land Area")
    private String LandArea;

    @CsvBindByName(column = "Housing Density")
    private String HousingDensity;

    public String getStateID() {
        return StateID;
    }
    public void setStateID(String stateID) {
        StateID = stateID;
    }
    public String getState() {
        return State;
    }
    public void setState(String state) {
        State = state;
    }
    public String getPopulationDensity() {
        return PopulationDensity;
    }
    public void setPopulationDensity(String populationDensity) {
        PopulationDensity = populationDensity;
    }
    public String getPopulation() {
        return Population;
    }
    public void setPopulation(String population) {
        Population = population;
    }
    public String getArea() {
        return Area;
    }
    public void setArea(String area) {
        Area = area;
    }
    public String getHousingUnits() {
        return HousingUnits;
    }
    public void setHousingUnits(String housingUnits) {
        HousingUnits = housingUnits;
    }
    public String getWaterArea() {
        return WaterArea;
    }
    public void setWaterArea(String waterArea) {
        WaterArea = waterArea;
    }
    public String getLandArea() {
        return LandArea;
    }
    public void setLandArea(String landArea) {
        LandArea = landArea;
    }
    public String getHousingDensity() {
        return HousingDensity;
    }
    public void setHousingDensity(String housingDensity)
    {
        HousingDensity = housingDensity;
    }
}


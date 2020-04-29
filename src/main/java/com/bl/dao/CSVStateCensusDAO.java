package com.bl.dao;
import com.bl.model.CSVStateCensus;
import com.bl.model.CSVStateCode;
import com.bl.model.CSVUSCensusData;
public class CSVStateCensusDAO
{
   private String SrNo;
    private String State;
    private String StateName;
    private int Population;
    private int AreaInSqKm;
    private int DensityPerSqKm;
    private String StateCode;
    private String TIN;

    private String StateID;
    private String PopulationDensity;
    private String Area;
    private String HousingUnits;
    private String WaterArea;
    private String LandArea;
    private String HousingDensity;
    private int TotalArea;

    CSVStateCensus indianStateCode;
    CSVStateCode stateCodee;
    CSVUSCensusData csvusCensusData;

    public CSVStateCensusDAO(CSVStateCode indiaStateCodeCSV) {
        this.SrNo = indiaStateCodeCSV.getSrNo();
        this.State = indiaStateCodeCSV.getStateName();
        this.StateCode = indiaStateCodeCSV.getStateCode();
        this.TIN = indiaStateCodeCSV.getTIN();
    }
    public CSVStateCensusDAO(CSVStateCensus indiaCensusCSV) {
        this.State = indiaCensusCSV.getState();
        this.Population = indiaCensusCSV.getPopulation();
        this.AreaInSqKm = indiaCensusCSV.getAreaInSqKm();
        this.DensityPerSqKm = indiaCensusCSV.getDensityPerSqKm();
    }
    public CSVStateCensusDAO(CSVUSCensusData indiaCensusCSV)
    {
        this.StateID=csvusCensusData.getStateID();
        this.PopulationDensity=csvusCensusData.getPopulationDensity();
        this.Area=csvusCensusData.getArea();
        this.HousingUnits=csvusCensusData.getHousingUnits();
        this.WaterArea=csvusCensusData.getWaterArea();
        this.LandArea=csvusCensusData.getLandArea();
        this.HousingDensity=csvusCensusData.getHousingDensity();
    }
   /* public CSVStateCensusDAO(Class<E> csvClass) {
    }*/
    public String getSrNo() {
        return SrNo;
    }
    public void setSrNo(String srNo) {
        this.SrNo = stateCodee.getSrNo();
    }
    public String getState() {
        return State;
    }
    public void setState(String state) {
        this.State = indianStateCode.getState();
    }
    public int getPopulation() {
        return Population;
    }
    public void setPopulation(int population) {
        this.Population = indianStateCode.getPopulation();
    }
    public int getAreaInSqKm() {
        return AreaInSqKm;
    }
    public void setAreaInSqKm(int areaInSqKm) {
        this.AreaInSqKm = indianStateCode.getAreaInSqKm();
    }
    public int getDensityPerSqKm() {
        return DensityPerSqKm;
    }
    public void setDensityPerSqKm(int densityPerSqKm) {
        this.DensityPerSqKm = indianStateCode.getDensityPerSqKm();
    }
    public String getStateName() {
        return StateName;
    }
    public void setStateName(String stateName) {
        StateName = stateCodee.getStateName();
    }

    public String getStateCode() {
        return StateCode;
    }
    public void setStateCode(String stateCode) {
        this.StateCode = stateCodee.getStateCode();
    }
    public String getTin() {
        return TIN;
    }
    public void setTin(String TIN) {
        this.TIN = stateCodee.getTIN();
    }
    public String getStateID() {
        return StateID;
    }
    public void setStateID(String stateID) {
        StateID = stateID;
    }
    public String getPopulationDensity() {
        return PopulationDensity;
    }
    public void setPopulationDensity(String populationDensity) {
        PopulationDensity = populationDensity;
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
    public void setHousingDensity(String housingDensity) {
        HousingDensity = housingDensity;
    }
    @Override
    public String toString() {
        return SrNo +
                ", " + State +
                ", " + StateName +
                "," + Population +
                "," + AreaInSqKm +
                "," + DensityPerSqKm +
                "," + StateCode +
                "," + TIN +
                "," + indianStateCode +
                //", " + stateCodee +
                 "," + StateID +
                "," + PopulationDensity +
                "," + Area +
                "," + HousingUnits +
                "," + WaterArea +
                "," + LandArea +
                "," + HousingDensity +
                "," + TotalArea ;
    }
}



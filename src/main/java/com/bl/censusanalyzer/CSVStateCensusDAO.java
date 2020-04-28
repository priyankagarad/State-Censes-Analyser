package com.bl.censusanalyzer;
import com.bl.model.CSVStateCensus;
import com.bl.model.CSVStateCode;
public class CSVStateCensusDAO<E>
{
    private String SrNo;
    private String State;
    private String StateName;
    private int Population;
    private int AreaInSqKm;
    private int DensityPerSqKm;
    private String StateCode;
    private String TIN;
    CSVStateCensus indianStateCode;
    CSVStateCode stateCodee;

    public CSVStateCensusDAO(CSVStateCode indiaStateCodeCSV)
    {
        this.SrNo = indiaStateCodeCSV.getSrNo();
        this.State = indiaStateCodeCSV.getStateName();
        this.StateCode = indiaStateCodeCSV.getStateCode();
        this.TIN = indiaStateCodeCSV.getTTN();
    }
    public CSVStateCensusDAO(CSVStateCensus indiaCensusCSV)
    {
        this.State = indiaCensusCSV.getState();
        this.Population = indiaCensusCSV.getPopulation();
        this.AreaInSqKm = indiaCensusCSV.getAreaInSqKm();
        this.DensityPerSqKm = indiaCensusCSV.getDensityPerSqKm();
    }
    public CSVStateCensusDAO(Class<E> csvClass) {
    }
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
        this.TIN = stateCodee.getTTN();
    }
}



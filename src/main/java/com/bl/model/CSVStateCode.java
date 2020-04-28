package com.bl.model;
import com.opencsv.bean.CsvBindByName;
public class CSVStateCode<E>
{
    @CsvBindByName(column = "SrNo")
    private String SrNo;
    @CsvBindByName(column = "StateName")
    private String StateName;
    @CsvBindByName(column = "StateCode")
    private String StateCode;
    @CsvBindByName(column = "TTN")
    private String TIN;

    public String getSrNo() {
        return SrNo;
    }

    public void setSrNo(String srNo) {
        SrNo = srNo;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    public String getStateCode() {
        return StateCode;
    }

    public void setStateCode(String stateCode) {
        StateCode = stateCode;
    }

    public String getTTN() {
        return TIN;
    }

    public void setTTN(String TTN) {
        this.TIN = TIN;
    }
}

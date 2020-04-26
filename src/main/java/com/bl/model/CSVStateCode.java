package com.bl.model;
import com.opencsv.bean.CsvBindByName;
public class CSVStateCode
{
    @CsvBindByName(column = "SrNo")
    private int SrNo;
    @CsvBindByName(column = "StateName")
    private int StateName;
    @CsvBindByName(column = "StateCode")
    private String StateCode;
    @CsvBindByName(column = "TTN")
    private int TTN;

    public int getSrNo()
    {
        return SrNo;
    }

    public void setSrNo(int SrNo)
    {
        this.SrNo=SrNo;
    }

    public int getStateName()
    {
        return StateName;
    }
    public void setStateName(int StateName)
    {
        this.StateName=StateName;
    }
    public int getTIN()
    {
        return TTN;
    }
    public void setTTN(int TTN)
    {
        this.TTN=TTN;
    }
    public String getStateCode()
    {
        return StateCode;
    }
    public void setStateCode(String StateCode)
    {
        StateCode=StateCode;
    }
}

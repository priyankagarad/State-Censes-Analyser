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
}

package com.bl.dto;
import com.opencsv.bean.CsvBindByName;
public class UsCensusCSV {
@CsvBindByName(column = "State",required = true)
    public String state;

@CsvBindByName(column="State Id",required = true)
    public double stateCode;

@CsvBindByName(column = "Population", required = true)
    public double population;

@CsvBindByName(column = "TotalArea",required = true)
    public double totalArea;

@CsvBindByName(column = "Population Density",required =true)
    public double densityPerSqKm;

@Override
public String toString() {
    return "UsCensusCSV{" +
            "state='" + state + '\'' +
            ", stateId=" + stateCode +
            ", population=" + population +
            ", totalArea=" + totalArea +
            ", populationDensity=" + densityPerSqKm +
            '}';
        }
}

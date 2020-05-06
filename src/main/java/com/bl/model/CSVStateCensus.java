package com.bl.model;
import com.opencsv.bean.CsvBindByName;
public class CSVStateCensus<E>
{
    @CsvBindByName(column = "State")
    private String State;
    @CsvBindByName(column = "Population")
    private int Population;
    @CsvBindByName(column = "AreaInSqKm")
    private int AreaInSqKm;
    @CsvBindByName(column = "DensityPerSqKm")
    private int DensityPerSqKm;

    public String getState()
    {
        return State;
    }

    public int getPopulation()
    {
        return Population;
    }

    public int getAreaInSqKm()
    {
        return AreaInSqKm;
    }

    public int getDensityPerSqKm()
    {
        return DensityPerSqKm;
    }
}


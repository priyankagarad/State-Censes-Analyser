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
    public void setState(String state)
    {
        this.State = state;
    }
    public int getPopulation()
    {
        return Population;
    }
    public void setPopulation(int population)
    {
        this.Population = population;
    }
    public int getAreaInSqKm()
    {
        return AreaInSqKm;
    }
    public void setAreaInSqKm(int areaInSqKm)
    {
        AreaInSqKm = areaInSqKm;
    }
    public int getDensityPerSqKm()
    {
        return DensityPerSqKm;
    }
    public void setDensityPerSqKm(int densityPerSqKm)
    {
        DensityPerSqKm = densityPerSqKm;
    }
}

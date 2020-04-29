package com.bl.censusanalyzer;
import com.bl.censusanalyser.exception.CSVBuilderException;
import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder
{
    public <E> Iterator<E> getFileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException;
    public  <E> Iterator<E> getFileList(Reader reader, Class<E> csvClass) throws CSVBuilderException;
}


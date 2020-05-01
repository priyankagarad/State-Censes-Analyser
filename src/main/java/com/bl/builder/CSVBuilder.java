package com.bl.builder;
import com.bl.censusanalyser.exception.CSVBuilderException;
import com.bl.censusanalyser.exception.StateCensusAnalyserException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.Reader;
import java.util.Iterator;
public class CSVBuilder implements ICSVBuilder
{
    @Override
    public <E> Iterator<E> getFileIterator(Reader reader,Class<E> csvClass) throws CSVBuilderException {
        return this.getCSVBean(reader,csvClass).iterator();
    }
    public <E> CsvToBean<E> getCSVBean(Reader reader, Class<E> csvClass) throws CSVBuilderException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder(reader);
            csvToBeanBuilder.withType(csvClass);
            return csvToBeanBuilder.build();
        }
        catch (RuntimeException e) {
            throw new CSVBuilderException(e.getMessage(),StateCensusAnalyserException.exceptionType.INCORRECT_FILE);
        }
    }
}
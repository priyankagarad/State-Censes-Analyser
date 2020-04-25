package com.bl.censusanalyser.exception;
public class CSVBuilderException extends Throwable
{
    enum exceptionType
    {
        FILE_NOT_FOUND,
        INCORRECT_FILE
    }
    public StateCensusAnalyserException.exceptionType type;
    public CSVBuilderException(String message,StateCensusAnalyserException.exceptionType type)
    {
        super(message);
        this.type=type;
    }
}
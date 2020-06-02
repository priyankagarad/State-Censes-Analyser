package com.bl.censusanalyser.exception;
public class StateCensusAnalyserException extends RuntimeException
{
    public enum exceptionType {
        FILE_NOT_FOUND, INCORRECT_FILE,NO_CENSUS_DATA;
    }

    public exceptionType exceptionTypeObject;
    public StateCensusAnalyserException(String message, exceptionType exceptionTypeObject) {
        super(message);
        this.exceptionTypeObject = exceptionTypeObject;
    }
}
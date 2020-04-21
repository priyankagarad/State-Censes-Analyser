package com.censusanalyser;
public class StateCensusAnalyserException extends Exception
{
    public enum exceptionType
    {
        FILE_NOT_FOUND ,WRONG_DELIMITER,WRONG_FILE_FORMATE
    }
    exceptionType exceptionTypeObject;
    public StateCensusAnalyserException(exceptionType exceptionTypeObject)
    {
        this.exceptionTypeObject=exceptionTypeObject;
    }
}

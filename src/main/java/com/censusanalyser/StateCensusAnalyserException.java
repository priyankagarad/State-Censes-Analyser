package com.censusanalyser;
public class StateCensusAnalyserException extends Exception
{
    public enum exceptionType
    {
        FILE_NOT_FOUND ,IMPROPER_FILE_TYPE
    }
    exceptionType exceptionTypeObject;
    public StateCensusAnalyserException(exceptionType exceptionTypeObject)
    {
        this.exceptionTypeObject=exceptionTypeObject;
    }
}

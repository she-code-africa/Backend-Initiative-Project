package com.backendinitiative.exceptions;

public class BackendInitiativeExceptions extends Exception{
    public BackendInitiativeExceptions(){}

    public BackendInitiativeExceptions(String message){
        super(message);
    }

    public BackendInitiativeExceptions(String message, Throwable cause){
        super(message, cause);
    }
}

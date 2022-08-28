package dev.code.digital_lending_microservice.exception;

public class LoanException extends RuntimeException{

    public LoanException(String message){
        super(message);
    }
}


package com.sejour.reservationsejour.exceptions;

public class AlreadyExistException extends RuntimeException{

    public AlreadyExistException(String message){
        super(message);
    }
}

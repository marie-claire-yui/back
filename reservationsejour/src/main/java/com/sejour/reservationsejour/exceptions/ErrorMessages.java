package com.sejour.reservationsejour.exceptions;

public enum ErrorMessages {


    NOT_FOUND("NOT FOUND"),
    ALREADY_EXIST("ALREADY EXISTE");


    ErrorMessages(String message) {
        this.message = message;
    }

    private String message;

    public String getMessage(){
        return message;
    }



}

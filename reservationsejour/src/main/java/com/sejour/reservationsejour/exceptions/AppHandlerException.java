package com.sejour.reservationsejour.exceptions;

import com.sejour.reservationsejour.responses.ErrorMesageResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class AppHandlerException {


    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object>  handlerNotFoundException(NotFoundException exception , WebRequest ws){

        ErrorMesageResponse errorMesageResponse = new ErrorMesageResponse();
        errorMesageResponse.setErrorMessage(exception.getMessage());
        errorMesageResponse.setDate(new Date());

        return new ResponseEntity<>(errorMesageResponse, new HttpHeaders() , HttpStatus.NOT_FOUND);


    }

    @ExceptionHandler(value = AlreadyExistException.class)
    public  ResponseEntity<Object> handlerAlreadyExistException(AlreadyExistException exception , WebRequest ws){

        ErrorMesageResponse errorMesageResponse = ErrorMesageResponse.builder()
                .errorMessage(exception.getMessage())
                .date(new Date())
                .build();

        return new ResponseEntity<>(errorMesageResponse, new HttpHeaders(), HttpStatus.CONFLICT);

    }

}

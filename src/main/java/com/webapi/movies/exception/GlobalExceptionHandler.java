package com.webapi.movies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleNotFoundException(MovieNotFoundException exc){
        ExceptionResponse res = new ExceptionResponse();

        res.setStatusCode(HttpStatus.NOT_FOUND.value());
        res.setMessage(exc.getMessage());

        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleBadRequestException(RuntimeException exc){
        ExceptionResponse res = new ExceptionResponse();

        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        res.setMessage(exc.getMessage());

        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
}

package com.s27083_bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.s27083_bank.exceptions.InvalidBalanceException;
import com.s27083_bank.exceptions.InvalidPeselException;
import com.s27083_bank.exceptions.UserNotFoundException;
import com.s27083_bank.exceptions.ValidationException;
import com.s27083_bank.model.request.ErrorResponse;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException exception){
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());

        if (exception.getErrors() != null && !exception.getErrors().isEmpty()){
            errorResponse = new ErrorResponse("Validation failed", exception.getErrors());
        }

        return ResponseEntity.badRequest().body(errorResponse);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(UserNotFoundException exception){
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());

        return ResponseEntity.status(404).body(errorResponse);
    }


    @ExceptionHandler(InvalidPeselException.class) 
        public ResponseEntity<ErrorResponse> handleInvalidPeselException(InvalidPeselException exception){
            ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());

            return ResponseEntity.badRequest().body(errorResponse);
        }

    @ExceptionHandler(InvalidBalanceException.class)
        public ResponseEntity<ErrorResponse> handleInvalidBalanceException(InvalidBalanceException exception){
            ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());

            return ResponseEntity.badRequest().body(errorResponse);
        }
        

    }


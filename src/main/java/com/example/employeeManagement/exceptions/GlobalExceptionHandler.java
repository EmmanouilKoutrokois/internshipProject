package com.example.employeeManagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Catch all exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // Log the exception (optional but recommended)
        e.printStackTrace();

        // Return the error message in response
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }
}

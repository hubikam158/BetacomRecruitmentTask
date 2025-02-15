package com.betacom.rekrutacja.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleException(UserAlreadyExistsException exc) {

        ErrorResponse errorResponse = new ErrorResponse();
        HttpStatus status = HttpStatus.CONFLICT;

        errorResponse.setStatus(status.value());
        errorResponse.setDescription(exc.getMessage());

        return new ResponseEntity<>(errorResponse, status);
    }
}

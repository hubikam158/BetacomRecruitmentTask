package com.betacom.rekrutacja.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UserNotAuthenticatedException.class)
    public ResponseEntity<ErrorResponse> handleException(UserNotAuthenticatedException exc) {

        ErrorResponse errorResponse = new ErrorResponse();
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        errorResponse.setStatus(status.value());
        errorResponse.setDescription(exc.getMessage());

        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleException(UserAlreadyExistsException exc) {

        ErrorResponse errorResponse = new ErrorResponse();
        HttpStatus status = HttpStatus.NO_CONTENT;

        errorResponse.setStatus(status.value());
        errorResponse.setDescription(exc.getMessage());

        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(UsernameNotFoundException exc) {

        ErrorResponse errorResponse = new ErrorResponse();
        HttpStatus status = HttpStatus.OK;

        errorResponse.setStatus(status.value());
        errorResponse.setDescription(exc.getMessage());

        return new ResponseEntity<>(errorResponse, status);
    }
}

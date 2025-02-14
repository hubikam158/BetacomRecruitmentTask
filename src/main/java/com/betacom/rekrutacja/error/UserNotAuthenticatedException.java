package com.betacom.rekrutacja.error;

public class UserNotAuthenticatedException extends RuntimeException {

    public UserNotAuthenticatedException(String message) {
        super(message);
    }
}

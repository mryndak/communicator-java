package com.communicator.exception;

public class UserNotFoundException extends RuntimeException {
    private final static String message = "Uzytkownik nie zostal znaleziony";
    public UserNotFoundException(){
        super(message);
    }
}

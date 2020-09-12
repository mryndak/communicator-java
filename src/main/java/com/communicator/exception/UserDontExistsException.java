package com.communicator.exception;

public class UserDontExistsException extends RuntimeException {
    private final static String message = "Uzytkownik nie istnieje";
    public UserDontExistsException(){
        super(message);
    }
}

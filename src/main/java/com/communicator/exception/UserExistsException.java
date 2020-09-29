package com.communicator.exception;

public class UserExistsException extends RuntimeException {
    private final static String message = "Uzytkownik juz istnieje";
    public UserExistsException(){
        super(message);
    }
}

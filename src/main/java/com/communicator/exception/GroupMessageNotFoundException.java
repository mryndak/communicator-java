package com.communicator.exception;

public class GroupMessageNotFoundException extends RuntimeException {
    private final static String message = "Konwersacja nie zostala znaleziona";
    public GroupMessageNotFoundException(){
        super(message);
    }
}

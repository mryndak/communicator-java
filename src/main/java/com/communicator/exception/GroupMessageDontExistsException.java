package com.communicator.exception;

public class GroupMessageDontExistsException extends RuntimeException {
    private final static String message = "Konwersacja nie istnieje";
    public GroupMessageDontExistsException(){
        super(message);
    }
}

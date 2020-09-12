package com.communicator.exception;

public class MessageDontExistsException extends RuntimeException {
    private final static String message = "Wiadomosc nie istnieje";
    public MessageDontExistsException(){
        super(message);
    }
}

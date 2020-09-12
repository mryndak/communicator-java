package com.communicator.exception;

public class MessageNotFoundException extends RuntimeException {
    private final static String message = "Wiadomosc nie zostal znaleziony";
    public MessageNotFoundException(){
        super(message);
    }
}

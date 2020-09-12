package com.communicator.exception;

public class ConversationNotFoundException extends RuntimeException {
    private final static String message = "Konwersacja nie zostala znaleziona";
    public ConversationNotFoundException(){
        super(message);
    }
}

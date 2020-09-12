package com.communicator.exception;

public class ConversationDontExistsException extends RuntimeException {
    private final static String message = "Konwersacja nie istnieje";
    public ConversationDontExistsException(){
        super(message);
    }
}

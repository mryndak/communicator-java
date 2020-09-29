package com.communicator.exception;

public class NotificationDontExistsException extends RuntimeException {
    private final static String message = "Powiadomienie nie istnieje";
    public NotificationDontExistsException(){
        super(message);
    }
}

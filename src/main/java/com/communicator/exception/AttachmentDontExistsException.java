package com.communicator.exception;

public class AttachmentDontExistsException extends RuntimeException {
    private final static String message = "Zalacznik nie istnieje";
    public AttachmentDontExistsException(){
        super(message);
    }
}

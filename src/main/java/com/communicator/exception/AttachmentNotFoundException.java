package com.communicator.exception;

public class AttachmentNotFoundException extends RuntimeException {
    private final static String message = "Zalacznik nie zostal znaleziony";
    public AttachmentNotFoundException(){
        super(message);
    }
}

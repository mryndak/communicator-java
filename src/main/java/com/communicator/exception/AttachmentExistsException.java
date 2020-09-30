package com.communicator.exception;

public class AttachmentExistsException extends RuntimeException {
    private final static String message = "Zalacznik juz istnieje";
    public AttachmentExistsException(){
        super(message);
    }
}

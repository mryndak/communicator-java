package com.communicator.exception;

public class FriendsListNotFoundException extends RuntimeException {
    private final static String message = "Lista nie zostal znaleziony";
    public FriendsListNotFoundException(){
        super(message);
    }
}

package com.communicator.exception;

public class FriendsListDontExistsException extends RuntimeException {
    private final static String message = "Lista nie istnieje";
    public FriendsListDontExistsException(){
        super(message);
    }
}

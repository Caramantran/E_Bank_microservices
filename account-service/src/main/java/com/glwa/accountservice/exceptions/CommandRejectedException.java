package com.glwa.accountservice.exceptions;

public class CommandRejectedException extends Exception{
    public CommandRejectedException(String message) {
        super(message);
    }
}

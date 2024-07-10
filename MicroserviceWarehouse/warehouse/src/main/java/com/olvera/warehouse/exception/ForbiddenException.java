package com.olvera.warehouse.exception;

public class ForbiddenException extends RuntimeException{

    public ForbiddenException() {
        super("message");
    }
}

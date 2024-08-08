package com.olvera.warehouse.product.exception;

public class ForbiddenException extends RuntimeException{

    public ForbiddenException() {
        super("message");
    }
}

package com.inside.exception;

public class JwtValidatingException extends RuntimeException{
    public JwtValidatingException(String message) {
        super(message);
    }
}

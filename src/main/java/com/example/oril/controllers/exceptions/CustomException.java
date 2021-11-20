package com.example.oril.controllers.exceptions;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}

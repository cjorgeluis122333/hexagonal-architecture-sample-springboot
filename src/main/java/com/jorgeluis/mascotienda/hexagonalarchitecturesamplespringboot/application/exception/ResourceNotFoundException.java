package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.application.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

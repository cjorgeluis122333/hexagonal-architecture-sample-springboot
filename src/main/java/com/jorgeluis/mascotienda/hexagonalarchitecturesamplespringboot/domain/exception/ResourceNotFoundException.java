package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
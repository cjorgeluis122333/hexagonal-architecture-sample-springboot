package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.exception;

public class LoanDeniedException extends BusinessException {
    public LoanDeniedException(String message) {
        super(message);
    }
}
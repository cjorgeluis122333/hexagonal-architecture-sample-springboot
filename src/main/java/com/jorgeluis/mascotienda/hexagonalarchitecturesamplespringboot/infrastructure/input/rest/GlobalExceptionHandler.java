package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.input.rest;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.exception.LoanDeniedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoanDeniedException.class)
    public ResponseEntity<ErrorResponse> handleLoanDenied(LoanDeniedException ex) {
        var error = new ErrorResponse("LOAN_REJECTED", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        var error = new ErrorResponse("INVALID_FORMAT", "Datos de entrada incorrectos");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
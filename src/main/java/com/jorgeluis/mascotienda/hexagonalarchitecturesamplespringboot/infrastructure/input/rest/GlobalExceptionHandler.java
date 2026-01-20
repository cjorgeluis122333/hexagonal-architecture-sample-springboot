package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.input.rest;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.exception.ResourceNotFoundException;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.exception.LoanDeniedException;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.input.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    // 1. Captura errores de lógica de negocio (Tu excepción personalizada)
    @ExceptionHandler(LoanDeniedException.class)
    public ResponseEntity<ErrorResponse> handleLoanDenied(LoanDeniedException ex) {
        ErrorResponse error = new ErrorResponse("LOAN_REJECTED", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // 2. Captura errores de validación de Spring (@Valid, @NotNull, etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        ErrorResponse error = new ErrorResponse("INVALID_FORMAT", "Los datos enviados son inválidos");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // 3. Not found Loan
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLoanNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse("LOAN_NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // 4. "Catcher" genérico para errores inesperados (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralError(Exception ex) {
        ErrorResponse error = new ErrorResponse("INTERNAL_SERVER_ERROR", "Ha ocurrido un error inesperado");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
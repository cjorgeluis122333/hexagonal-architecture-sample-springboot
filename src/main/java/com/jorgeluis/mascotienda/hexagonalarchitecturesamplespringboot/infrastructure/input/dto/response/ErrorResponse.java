package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.input.dto.response;

import java.time.LocalDateTime;

public record ErrorResponse(String code, String message, LocalDateTime timestamp) {
    // Constructor compacto para facilitar el uso
    public ErrorResponse(String code, String message) {
        this(code, message, LocalDateTime.now());
    }
}

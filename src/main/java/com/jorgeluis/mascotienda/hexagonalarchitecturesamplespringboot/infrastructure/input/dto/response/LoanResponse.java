package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.input.dto.response;

public record LoanResponse(
        Long id,
        boolean approved,
        String message
) {
}

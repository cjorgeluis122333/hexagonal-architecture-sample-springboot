package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.input.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record LoanRequest(
        @Positive BigDecimal amount,
        @NotBlank String name
) {}
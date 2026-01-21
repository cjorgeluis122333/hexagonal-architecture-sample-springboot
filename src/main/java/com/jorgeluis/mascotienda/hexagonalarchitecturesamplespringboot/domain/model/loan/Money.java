package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.loan;

import java.math.BigDecimal;

public record  Money(BigDecimal amount, String currency) {
    public Money {
        if (amount.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Amount cannot be negative");
    }
    // Métodos de negocio aquí: plus(), minus(), etc.
}
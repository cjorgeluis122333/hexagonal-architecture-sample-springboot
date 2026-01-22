package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.loan;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

public record  Money(BigDecimal amount, String currency) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public Money {
        if (amount.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Amount cannot be negative");
    }
    // Métodos de negocio aquí: plus(), minus(), etc.
}
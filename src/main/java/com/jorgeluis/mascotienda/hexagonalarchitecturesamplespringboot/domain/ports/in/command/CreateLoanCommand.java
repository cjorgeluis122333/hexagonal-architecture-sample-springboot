package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.command;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.Money;

public record CreateLoanCommand(
        Money money,
        String borrower
) {
    public CreateLoanCommand {
        if (borrower == null || borrower.isBlank()) {
            throw new IllegalArgumentException("Borrower name is required");
        }
    }
}

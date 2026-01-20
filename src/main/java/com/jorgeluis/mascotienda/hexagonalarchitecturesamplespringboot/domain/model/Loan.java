package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Loan {
    private final Long id;
    private final Money amount;
    private final String borrower;
    private boolean approved;

    public Loan(Long id, Money amount, String borrower) {
        this.id = id;
        this.amount = amount;
        this.borrower = borrower;
        this.approved = false;
    }

    // Regla de negocio: Un préstamo de más de 100k nunca se aprueba automáticamente
    public void evaluate() {
        if (this.amount.amount().compareTo(new BigDecimal("100000")) < 0) {
            this.approved = true;
        }
    }

}

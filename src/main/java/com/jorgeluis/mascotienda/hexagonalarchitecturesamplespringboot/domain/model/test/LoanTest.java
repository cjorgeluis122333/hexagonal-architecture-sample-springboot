package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model;

import org.junit.jupiter.api.Test; // JUnit 5

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue; // JUnit Assertions
import java.math.BigDecimal;

class LoanTest {
    @Test
    void shouldApproveLoanWhenAmountIsLow() {
        Money amount = new Money(new BigDecimal("5000"), "USD");
        Loan loan = new Loan(1L, amount, "John Doe");

        loan.evaluate();

        // Si usas Lombok en Loan, asegúrate de tener @Getter
        assertTrue(loan.isApproved());
    }
}
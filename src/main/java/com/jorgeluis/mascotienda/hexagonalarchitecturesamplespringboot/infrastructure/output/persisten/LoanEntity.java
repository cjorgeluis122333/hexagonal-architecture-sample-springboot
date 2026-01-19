package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.output.persisten;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "loans")
public class LoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private String borrower;
    private boolean approved;
}
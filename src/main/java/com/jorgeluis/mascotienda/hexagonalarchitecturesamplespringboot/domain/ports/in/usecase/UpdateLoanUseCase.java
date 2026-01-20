package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.usecase;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.Loan;

import java.util.Optional;

public interface UpdateLoanUseCase {
    Optional<Loan> updateLoan(Loan loan, Long id);
}

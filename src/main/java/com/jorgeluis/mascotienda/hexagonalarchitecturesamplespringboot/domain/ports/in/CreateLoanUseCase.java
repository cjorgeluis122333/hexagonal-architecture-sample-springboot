package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.Loan;

public interface CreateLoanUseCase {
    Loan createLoan(CreateLoanCommand command);
}

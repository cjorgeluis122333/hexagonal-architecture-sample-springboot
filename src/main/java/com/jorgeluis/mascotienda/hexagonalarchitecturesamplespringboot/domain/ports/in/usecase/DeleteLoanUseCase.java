package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.usecase;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.Loan;

public interface DeleteLoanUseCase {
    boolean deleteLoanUseCase(Long loanId);
}

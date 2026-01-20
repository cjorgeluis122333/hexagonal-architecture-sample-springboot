package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.application.usecase;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.Loan;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.usecase.UpdateLoanUseCase;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.out.LoanRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UpdateLoanUseCaseImplement implements UpdateLoanUseCase {
    final LoanRepositoryPort loanRepositoryPort;

    @Override
    public Optional<Loan> updateLoan(Loan loan, Long id) {
        return loanRepositoryPort.update(loan);
    }
}

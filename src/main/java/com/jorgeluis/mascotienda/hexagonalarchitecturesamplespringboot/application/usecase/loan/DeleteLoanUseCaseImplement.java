package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.application.usecase.loan;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.usecase.loan.DeleteLoanUseCase;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.out.LoanRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteLoanUseCaseImplement implements DeleteLoanUseCase {
    private final LoanRepositoryPort loanRepositoryPort;


    @Override
    public boolean deleteLoanUseCase(Long id) {
        return loanRepositoryPort.deleteById(id);
    }
}

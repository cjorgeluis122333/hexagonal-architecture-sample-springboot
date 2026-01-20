package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.application.usecase;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.application.exception.ResourceNotFoundException;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.Loan;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.usecase.FindLoanUseCase;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.out.LoanRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FindLoanUseCaseImplement implements FindLoanUseCase {
    private final LoanRepositoryPort loanRepositoryPort;

    @Override
    public Loan findLoanById(Long id) {
        return loanRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el préstamo con ID: " + id));
    }

    @Override
    public List<Loan> findAllLoans() {
        List<Loan> loans = loanRepositoryPort.findAll();
        if (loans.isEmpty()) {
            return List.of();
        }
        return loans;
    }
}

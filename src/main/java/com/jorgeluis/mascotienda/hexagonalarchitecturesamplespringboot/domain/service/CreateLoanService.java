package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.service;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.Loan;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.CreateLoanUseCase;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.out.LoanRepositoryPort;

public class CreateLoanService implements CreateLoanUseCase {
    private final LoanRepositoryPort repositoryPort;

    public CreateLoanService(LoanRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Loan createLoan(CreateLoanCommand command) {
        Loan loan = new Loan(null, command.money(), command.borrower());
        loan.evaluate(); // Lógica de negocio
        repositoryPort.save(loan); // Guardado a través del puerto
        return loan;
    }
}

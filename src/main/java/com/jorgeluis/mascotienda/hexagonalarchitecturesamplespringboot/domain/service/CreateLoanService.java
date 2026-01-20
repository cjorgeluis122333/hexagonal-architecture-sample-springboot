package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.service;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.Loan;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.command.CreateLoanCommand;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.out.LoanRepositoryPort;

public class CreateLoanService {

    // 1. Debes declarar el puerto para poder usarlo (aunque sea para que lo use la App Layer después)
    private final LoanRepositoryPort repositoryPort;

    // 2. El constructor que espera la clase LoanConfig
    public CreateLoanService(LoanRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    public Loan execute(CreateLoanCommand command) {
        // Aquí creamos el objeto de dominio
        Loan loan = new Loan(null, command.money(), command.borrower());

        // Ejecutamos lógica de negocio
        loan.evaluate();

        return loan;
    }
}
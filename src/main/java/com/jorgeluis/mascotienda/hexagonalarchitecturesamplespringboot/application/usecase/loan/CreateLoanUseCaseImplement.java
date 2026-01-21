package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.application.usecase.loan;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.loan.Loan;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.command.CreateLoanCommand;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.usecase.loan.CreateLoanUseCase;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.out.LoanRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateLoanUseCaseImplement implements CreateLoanUseCase {
    private final LoanRepositoryPort loanRepositoryPort;

    @Override
    public Loan createLoan(CreateLoanCommand command) {
        // Aquí creamos el objeto de dominio
        Loan loan = new Loan(null, command.money(), command.borrower());
        // Ejecutamos lógica de negocio
        loan.evaluate();
        // 2. La aplicación decide persistir el resultado
        return loanRepositoryPort.save(loan);
    }
}

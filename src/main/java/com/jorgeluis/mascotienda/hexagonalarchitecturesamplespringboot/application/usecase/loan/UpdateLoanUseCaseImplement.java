package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.application.usecase.loan;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.loan.Loan;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.command.CreateLoanCommand;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.usecase.loan.UpdateLoanUseCase;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.out.LoanRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateLoanUseCaseImplement implements UpdateLoanUseCase {
    final LoanRepositoryPort loanRepositoryPort;


    @Override
    public Loan updateLoan(CreateLoanCommand command, Long id) {
        Loan loan = new Loan(id, command.money(), command.borrower());
        loan.evaluate();
        return loanRepositoryPort.update(loan);
    }
}

package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.application.service;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.loan.Loan;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.command.CreateLoanCommand;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.usecase.loan.CreateLoanUseCase;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.usecase.loan.DeleteLoanUseCase;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.usecase.loan.FindLoanUseCase;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.usecase.loan.UpdateLoanUseCase;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class LoanApplicationService implements CreateLoanUseCase, DeleteLoanUseCase, UpdateLoanUseCase, FindLoanUseCase {
    private final CreateLoanUseCase createLoanUseCase;
    private final DeleteLoanUseCase deleteLoanUseCase;
    private final FindLoanUseCase findLoanUseCase;
    private final UpdateLoanUseCase updateLoanUseCase;

    @Override
    public Loan createLoan(CreateLoanCommand command) {
        return createLoanUseCase.createLoan(command);
    }

    @Override
    public boolean deleteLoanUseCase(Long id) {
        return deleteLoanUseCase.deleteLoanUseCase(id);
    }

    @Override
    public Loan findLoanById(Long id) {
        return findLoanUseCase.findLoanById(id);
    }

    @Override
    public List<Loan> findAllLoans() {
        return findLoanUseCase.findAllLoans();
    }

    @Override
    public Loan updateLoan(CreateLoanCommand loan, Long id) {
        return updateLoanUseCase.updateLoan(loan, id);
    }
}
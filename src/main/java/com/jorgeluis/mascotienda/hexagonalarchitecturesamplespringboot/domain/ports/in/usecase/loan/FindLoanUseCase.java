package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.usecase.loan;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.loan.Loan;

import java.util.List;

public interface FindLoanUseCase {
    Loan findLoanById(Long id); // Para buscar uno solo

    List<Loan> findAllLoans();
}

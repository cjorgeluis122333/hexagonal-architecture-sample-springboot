package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.out;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanRepositoryPort {
    Loan save(Loan loan);
    Optional<Loan> findById(Long id); // Para buscar uno solo
    List<Loan> findAll();
}

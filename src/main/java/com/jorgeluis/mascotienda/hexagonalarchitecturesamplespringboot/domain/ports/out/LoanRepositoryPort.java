package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.out;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanRepositoryPort {
    //Consult
    Optional<Loan> findById(Long id);
    List<Loan> findAll();

    //Update
    Optional<Loan> update(Loan loan);

    //Insert
    Loan save(Loan loan);

    //Delete
    boolean deleteById(Long id);
}

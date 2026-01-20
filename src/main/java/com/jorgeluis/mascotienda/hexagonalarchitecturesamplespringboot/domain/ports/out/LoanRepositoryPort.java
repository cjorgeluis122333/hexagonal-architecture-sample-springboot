package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.out;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.application.exception.ResourceNotFoundException;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanRepositoryPort {
    //Consult
    Optional<Loan> findById(Long id);
    List<Loan> findAll();

    //Update
    Loan update(Loan loan) throws ResourceNotFoundException;

    //Insert
    Loan save(Loan loan);

    //Delete
    boolean deleteById(Long id);
}

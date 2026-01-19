package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.out;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.Loan;

public interface LoanRepositoryPort {
    void save(Loan loan);
}

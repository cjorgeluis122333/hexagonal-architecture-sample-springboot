package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.query;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.Loan;

import java.util.List;

public interface GetLoanQuery {
    List<Loan> findAllActive();
}

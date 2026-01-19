package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.output.persisten.repository;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.output.persisten.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaLoanRepository extends JpaRepository<LoanEntity, Long> {
}

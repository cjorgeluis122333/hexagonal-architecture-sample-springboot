package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.output.persisten;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.Loan;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.out.LoanRepositoryPort;
import org.springframework.stereotype.Component;

@Component
public class JpaLoanAdapter implements LoanRepositoryPort {
    private final JpaLoanRepository repository; // El interface de Spring Data

    public JpaLoanAdapter(JpaLoanRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Loan loan) {
        // Mapeo: Dominio -> Entidad
        LoanEntity entity = new LoanEntity(null, loan.getAmount().amount(), loan.getBorrower(), loan.isApproved());
        repository.save(entity);
    }
}

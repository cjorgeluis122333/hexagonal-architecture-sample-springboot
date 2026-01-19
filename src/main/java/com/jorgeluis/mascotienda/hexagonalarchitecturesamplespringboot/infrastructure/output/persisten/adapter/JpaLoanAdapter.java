package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.output.persisten.adapter;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.Loan;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.Money;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.out.LoanRepositoryPort;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.output.persisten.entity.LoanEntity;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.output.persisten.repository.JpaLoanRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaLoanAdapter implements LoanRepositoryPort {
    private final JpaLoanRepository repository; // El interface de Spring Data

    public JpaLoanAdapter(JpaLoanRepository repository) {
        this.repository = repository;
    }

    @Override
    public Loan save(Loan loan) {
        // Mapeo: Dominio -> Entidad
        LoanEntity entity = new LoanEntity(null, loan.getAmount().amount(), loan.getBorrower(), loan.isApproved());
        repository.save(entity);
        return loan;
    }

    @Override
    public Optional<Loan> findById(Long id) {
        return repository.findById(id).map(this::mapToDomain); // Convertimos la entidad a dominio
    }

    @Override
    public List<Loan> findAll() {
        return repository.findAll().stream().map(this::mapToDomain) // Convertimos cada entidad de la lista
                .toList();
    }

    // Método privado para evitar repetir código de mapeo (Nivel Senior)
    private Loan mapToDomain(LoanEntity entity) {
        return new Loan(entity.getId(), new Money(entity.getAmount(), "Usd"), entity.getBorrower());
    }
}

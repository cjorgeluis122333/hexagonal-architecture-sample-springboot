package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.output.persisten.adapter;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.application.exception.ResourceNotFoundException;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.Loan;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.Money;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.out.LoanRepositoryPort;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.output.persisten.entity.LoanEntity;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.output.persisten.repository.JpaLoanRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class JpaLoanAdapter implements LoanRepositoryPort {
    private final JpaLoanRepository repository; // El interface de Spring Data


    @Override
    public Optional<Loan> findById(Long id) {
        return repository.findById(id).map(this::mapToDomain); // Convertimos la entidad a dominio
    }

    @Override
    public List<Loan> findAll() {
        return repository.findAll().stream().map(this::mapToDomain) // Convertimos cada entidad de la lista
                .toList();
    }

    @Override
    public Loan save(Loan loan) {
        // Mapeo: Dominio -> Entidad
        LoanEntity entity = new LoanEntity(null, loan.getAmount().amount(), loan.getBorrower(), loan.isApproved());
        repository.save(entity);
        return loan;
    }

    @Override
    public Loan update(Loan loan) throws ResourceNotFoundException {
        if (repository.existsById(loan.getId())) {
            LoanEntity entity = new LoanEntity(loan.getId(), loan.getAmount().amount(), loan.getBorrower(), loan.isApproved());
            repository.save(entity);
            return loan;
        }
        throw new ResourceNotFoundException("Loan with id " + loan.getId() + " does not exist");
    }

    @Override
    public boolean deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }


    // Método privado para evitar repetir código de mapeo (Nivel Senior)
    private Loan mapToDomain(LoanEntity entity) {
        val loan = new Loan(entity.getId(), new Money(entity.getAmount(), "Usd"), entity.getBorrower());
        loan.setApproved(entity.isApproved());
        return loan;
    }
}

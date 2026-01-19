package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.application.service;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.application.exception.ResourceNotFoundException;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.Loan;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.command.CreateLoanCommand;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.usecase.CreateLoanUseCase;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.out.LoanRepositoryPort;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.service.CreateLoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanApplicationService implements CreateLoanUseCase {

    private final CreateLoanService createLoanDomainService; // Servicio de Dominio
    private final LoanRepositoryPort loanRepositoryPort;     // Puerto de Salida

    @Override
    @Transactional
    public Loan createLoan(CreateLoanCommand command) {
        // 1. Delegamos la lógica compleja al dominio
        Loan loanEvaluated = createLoanDomainService.execute(command);

        // 2. La aplicación decide persistir el resultado
        return loanRepositoryPort.save(loanEvaluated);
    }
    @Transactional(readOnly = true)
    public Loan findById(Long id) {
        return loanRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el préstamo con ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<Loan> findAll() {
        List<Loan> loans = loanRepositoryPort.findAll();
        if (loans.isEmpty()) {
            return List.of();
        }
        return loans;
    }
}
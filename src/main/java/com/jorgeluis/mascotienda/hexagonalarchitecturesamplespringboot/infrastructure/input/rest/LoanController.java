package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.input.rest;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.application.service.LoanApplicationService;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.Loan;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.Money;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.command.CreateLoanCommand;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.input.dto.request.LoanRequest;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.input.dto.response.LoanResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@AllArgsConstructor
public class LoanController {

    private final LoanApplicationService loanService; // Para los GET

    // Dentro de LoanController.java
    @PostMapping
    public ResponseEntity<LoanResponse> create(@Valid @RequestBody LoanRequest request) {

        // 1. Transformamos el Request (Infra) a un Command (Dominio)
        CreateLoanCommand command = new CreateLoanCommand(new Money(request.amount(), "USD"), request.name());

        // 2. Ejecutamos el Caso de Uso
        Loan loan = loanService.createLoan(command);

        // 3. Transformamos el Resultado (Dominio) a un Response (Infra/JSON)
        LoanResponse response = new LoanResponse(loan.getId(), loan.isApproved(), loan.isApproved() ? "Préstamo aprobado automáticamente" : "Pendiente de revisión");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanResponse> update(@Valid @RequestBody LoanRequest request, @PathVariable Long id) {
        // 1. Transformamos el Request (Infra) a un Command (Dominio)
        CreateLoanCommand command = new CreateLoanCommand(new Money(request.amount(), "USD"), request.name());

        // 2. Ejecutamos el Caso de Uso
        Loan loan = loanService.updateLoan(command, id);

        // 3. Transformamos el Resultado (Dominio) a un Response (Infra/JSON)
        LoanResponse response = new LoanResponse(loan.getId(), loan.isApproved(), loan.isApproved() ? "Préstamo aprobado automáticamente" : "Pendiente de revisión");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (loanService.deleteLoanUseCase(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getById(@PathVariable Long id) {
        return ResponseEntity.ok(loanService.findLoanById(id));
    }

    @GetMapping
    public ResponseEntity<List<Loan>> getAll() {
        return ResponseEntity.ok(loanService.findAllLoans());
    }


}
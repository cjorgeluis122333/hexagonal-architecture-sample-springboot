package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.input.rest;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.Loan;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.Money;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.CreateLoanCommand;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.CreateLoanUseCase;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.input.dto.request.LoanRequest;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.input.dto.response.LoanResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    private final CreateLoanUseCase createLoanUseCase;

    public LoanController(CreateLoanUseCase createLoanUseCase) {
        this.createLoanUseCase = createLoanUseCase;
    }

    // Dentro de LoanController.java
    @PostMapping
    public ResponseEntity<LoanResponse> create(@Valid @RequestBody LoanRequest request) {

        // 1. Transformamos el Request (Infra) a un Command (Dominio)
        CreateLoanCommand command = new CreateLoanCommand(
                new Money(request.amount(), "USD"),
                request.name()
        );

        // 2. Ejecutamos el Caso de Uso
        Loan loan = createLoanUseCase.createLoan(command);

        // 3. Transformamos el Resultado (Dominio) a un Response (Infra/JSON)
        LoanResponse response = new LoanResponse(
                loan.getId(),
                loan.isApproved(),
                loan.isApproved() ? "Préstamo aprobado automáticamente" : "Pendiente de revisión"
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


//    @PostMapping
//    public ResponseEntity<LoanResponse> create(@RequestBody LoanRequest request) {
//        var command = new CreateLoanCommand(new Money(request.amount(), "USD"), request.name());
//        var loan = createLoanUseCase.createLoan(command);
//        return ResponseEntity.ok(new LoanResponse(loan.getId(), loan.isApproved(),""));
//    }

}
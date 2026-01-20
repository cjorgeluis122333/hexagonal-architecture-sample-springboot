package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.config;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.usecase.CreateLoanUseCase;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.out.LoanRepositoryPort;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.service.CreateLoanService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoanConfig {
    @Bean
    public CreateLoanService createLoanDomainService(LoanRepositoryPort loanRepositoryPort) {
        // Ahora Java sí encuentra un constructor que acepte LoanRepositoryPort
        return new CreateLoanService(loanRepositoryPort);
    }
}

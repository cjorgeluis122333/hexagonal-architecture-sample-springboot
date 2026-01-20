package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.config;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.application.usecase.CreateLoanUseCaseImplement;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.application.usecase.DeleteLoanUseCaseImplement;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.application.usecase.FindLoanUseCaseImplement;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.application.usecase.UpdateLoanUseCaseImplement;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.usecase.CreateLoanUseCase;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.usecase.DeleteLoanUseCase;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.usecase.FindLoanUseCase;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.usecase.UpdateLoanUseCase;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.out.LoanRepositoryPort;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.output.persisten.adapter.JpaLoanAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoanConfig {

    //========================================    PORT to ADAPTER
    // (domain to infrastructure)
    @Bean
    LoanRepositoryPort loanRepositoryPort(JpaLoanAdapter jpaLoanAdapter) {
        return jpaLoanAdapter;
    }


    //=======================================    USE CASES
    //Bind (Domain to Infrastructure)
    //UseCase  If received a param you have to  pass the param of the attribute in the param of the methode
    @Bean
    CreateLoanUseCase createLoanUseCase(LoanRepositoryPort loanRepositoryPort) {
        return new CreateLoanUseCaseImplement(loanRepositoryPort);
    }

    @Bean
    DeleteLoanUseCase deleteLoanUseCase(LoanRepositoryPort loanRepositoryPort) {
        return new DeleteLoanUseCaseImplement(loanRepositoryPort);
    }

    @Bean
    FindLoanUseCase findLoanUseCase(LoanRepositoryPort loanRepositoryPort) {
        return new FindLoanUseCaseImplement(loanRepositoryPort);
    }

    @Bean
    UpdateLoanUseCase updateLoanUseCase(LoanRepositoryPort loanRepositoryPort) {
        return new UpdateLoanUseCaseImplement(loanRepositoryPort);
    }
    //=======================================    Service
    //Application to Application  <-- Domain  (Require domain interface)


}

package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.config;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.application.service.LoanApplicationService;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.application.service.TaskApplicationService;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.application.usecase.loan.CreateLoanUseCaseImplement;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.application.usecase.loan.DeleteLoanUseCaseImplement;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.application.usecase.loan.FindLoanUseCaseImplement;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.application.usecase.loan.UpdateLoanUseCaseImplement;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.application.usecase.task.GetAdditionalTaskInfoUseCaseImpl;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.usecase.loan.CreateLoanUseCase;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.usecase.loan.DeleteLoanUseCase;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.usecase.loan.FindLoanUseCase;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.usecase.loan.UpdateLoanUseCase;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.usecase.task.GetAdditionalTaskInfoUseCase;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.out.ExternalServicePort;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.out.LoanRepositoryPort;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.output.external_api.adapter.ExternalServiceAdapter;
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

    @Bean
    ExternalServicePort externalServicePort(ExternalServiceAdapter externalServiceAdapter) {
        return externalServiceAdapter;
    }


    //=======================================    USE CASES
    //Bind (Domain to Infrastructure)
    //UseCase  If received a param you have to  pass the param of the attribute in the param of the methode

    //Loan
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

    //Task
    @Bean
    GetAdditionalTaskInfoUseCase getAdditionalTaskInfoUseCase(ExternalServicePort externalServicePort) {
        return new GetAdditionalTaskInfoUseCaseImpl(externalServicePort);
    }

    //=======================================    SERVICE
    //Application to Application  <-- Domain  (Require domain interface)

    //Loan
    @Bean
    LoanApplicationService loanApplicationService(CreateLoanUseCase createLoanUseCase, UpdateLoanUseCase updateLoanUseCase, DeleteLoanUseCase deleteLoanUseCase, FindLoanUseCase findLoanUseCase) {
        return new LoanApplicationService(createLoanUseCase, deleteLoanUseCase, findLoanUseCase, updateLoanUseCase);
    }

    //Task
    @Bean
    TaskApplicationService taskApplicationService(GetAdditionalTaskInfoUseCase getAdditionalTaskInfoUseCase) {
        return new TaskApplicationService(getAdditionalTaskInfoUseCase);
    }

}

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
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.output.persisten.adapter.RedisLoanAdapter;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.output.persisten.repository.JpaLoanRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoanConfig {

    //========================================    PORT to ADAPTER
    // (domain to infrastructure)

    //If I use redis always prefer  the redis adaptor so  will be redundant use Two LoanRepositoryPort
    @Bean
    LoanRepositoryPort loanRepositoryPort(RedisLoanAdapter redisLoanAdapter) {
        return redisLoanAdapter;
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


    //=======================================    SERVICE
    //Application to Application  <-- Domain  (Require domain interface)

    //Loan
    @Bean
    LoanApplicationService loanApplicationService(CreateLoanUseCase createLoanUseCase, UpdateLoanUseCase updateLoanUseCase, DeleteLoanUseCase deleteLoanUseCase, FindLoanUseCase findLoanUseCase) {
        return new LoanApplicationService(createLoanUseCase, deleteLoanUseCase, findLoanUseCase, updateLoanUseCase);
    }


}

package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.config;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.application.service.TaskApplicationService;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.application.usecase.task.GetAdditionalTaskInfoUseCaseImpl;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.usecase.task.GetAdditionalTaskInfoUseCase;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.out.ExternalServicePort;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.output.external_api.adapter.ExternalServiceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskConfig {

    //========================================    PORT to ADAPTER
    @Bean
    ExternalServicePort externalServicePort(ExternalServiceAdapter externalServiceAdapter) {
        return externalServiceAdapter;
    }

    //=======================================    USE CASES

    @Bean
    GetAdditionalTaskInfoUseCase getAdditionalTaskInfoUseCase(ExternalServicePort externalServicePort) {
        return new GetAdditionalTaskInfoUseCaseImpl(externalServicePort);
    }
    //=======================================    SERVICE

    @Bean
    TaskApplicationService taskApplicationService(GetAdditionalTaskInfoUseCase getAdditionalTaskInfoUseCase) {
        return new TaskApplicationService(getAdditionalTaskInfoUseCase);
    }

}

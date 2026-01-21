package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.application.service;


import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.task.AdditionalTaskInfo;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.usecase.task.GetAdditionalTaskInfoUseCase;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class TaskApplicationService implements GetAdditionalTaskInfoUseCase {

    private final GetAdditionalTaskInfoUseCase getAdditionalTaskInfoUseCase;

    @Override
    public AdditionalTaskInfo getAdditionalTaskInfo(Long taskId) {
        return getAdditionalTaskInfoUseCase.getAdditionalTaskInfo(taskId);
    }
}

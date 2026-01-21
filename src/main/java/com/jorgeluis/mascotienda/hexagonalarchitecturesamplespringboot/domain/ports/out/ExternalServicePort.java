package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.out;


import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.task.AdditionalTaskInfo;

public interface ExternalServicePort {
    AdditionalTaskInfo getAdditionalTaskInfo(Long taskId);
}

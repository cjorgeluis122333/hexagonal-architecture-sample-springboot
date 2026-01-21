package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AdditionalTaskInfo {
    private Long userId;
    private String userName;
    private String userEmail;


}

package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.infrastructure.output.external_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class JsonPlaceholderUser {
    private Long id;
    private String name;
    private String email;
}

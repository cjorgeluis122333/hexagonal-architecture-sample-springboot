package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.command;

import java.time.LocalDateTime;

/*
En lugar de un comando genérico de "Update", los sistemas de calidad usan comandos que
representan acciones del mundo real. Esto es lo que llamamos Domain Driven Design (DDD).
Escenario: Aprobar o rechazar un préstamo manualmente.
 */
public record ApproveLoanCommand(
        Long loanId,
        String officerId,   // Quién lo aprueba
        LocalDateTime approvalDate
) {
    public ApproveLoanCommand {
        if (approvalDate.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Cannot approve in the future");
        }
    }
}

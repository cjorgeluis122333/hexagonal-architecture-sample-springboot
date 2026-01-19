package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.command;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.Money;

import java.util.Objects;

/**
 * Escenario: Cambiar el destino y el monto de un préstamo. Ubicación:
 * @param loanId
 * @param newAmount
 * @param reason
 */
public record UpdateLoanCommand(
        Long loanId,        // Obligatorio para saber qué actualizar
        Money newAmount,    // Nuevo valor
        String reason       // Auditoría: por qué se cambia
) {
    public UpdateLoanCommand {
        Objects.requireNonNull(loanId, "ID cannot be null");
        if (reason == null || reason.length() < 10) {
            throw new IllegalArgumentException("Reason must be at least 10 characters");
        }
    }
}

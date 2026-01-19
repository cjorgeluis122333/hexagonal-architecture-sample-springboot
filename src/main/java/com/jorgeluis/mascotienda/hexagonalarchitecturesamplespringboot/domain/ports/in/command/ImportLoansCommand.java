package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.command;

import java.util.List;

public record ImportLoansCommand(
        String sourceSystem,
        List<CreateLoanCommand> loansToImport
) {
    public ImportLoansCommand {
        if (loansToImport == null || loansToImport.isEmpty()) {
            throw new IllegalArgumentException("List of loans cannot be empty");
        }
    }
}

package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.command;

public record DeleteRecordCommand(
        Long recordId,
        String currentUserId,
        String userRole
) {
    public boolean isAuthorized() {
        return "ADMIN".equals(userRole);
    }
}

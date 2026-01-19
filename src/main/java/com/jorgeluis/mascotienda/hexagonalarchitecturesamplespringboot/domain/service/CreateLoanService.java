package com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.service;

import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.model.Loan;
import com.jorgeluis.mascotienda.hexagonalarchitecturesamplespringboot.domain.ports.in.command.CreateLoanCommand;


public class CreateLoanService{

    public Loan execute(CreateLoanCommand command) {
        Loan loan = new Loan(null, command.money(), command.borrower());
        loan.evaluate(); // Aquí está el "negocio"
        return loan; // Devolvemos el objeto procesado, pero NO lo guardamos aquí aún
    }



}

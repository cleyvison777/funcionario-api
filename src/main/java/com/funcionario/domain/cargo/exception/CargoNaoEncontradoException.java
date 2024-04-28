package com.funcionario.domain.cargo.exception;

import com.funcionario.domain.exception.EntidadeNaoEncontradaException;

import java.io.Serial;

public class CargoNaoEncontradoException extends EntidadeNaoEncontradaException {
    @Serial
    private static final long serialVersionUID = 1L;
    public CargoNaoEncontradoException(String message) {
        super(message);
    }
    public CargoNaoEncontradoException(Long cargoId){
        this(String.format("Não existe um cadastro para o cargo com o códico %d", cargoId));
    }
}

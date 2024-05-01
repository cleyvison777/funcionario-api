package com.funcionario.domain.empregado.exception;

import com.funcionario.domain.exception.EntidadeNaoEncontradaException;

import java.io.Serial;

public class EmpregadoNaoEncontradoException extends EntidadeNaoEncontradaException {
    @Serial
    private static final long serialVersionUID = 1L;

    public EmpregadoNaoEncontradoException(String message) {
        super(message);
    }
    public EmpregadoNaoEncontradoException(Long empregadoId){
        this(String.format("Não existe um cadastro para o funcionario com o códico %d", empregadoId));

    }
}

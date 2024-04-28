package com.funcionario.domain.exception;

import java.io.Serial;

public class EntidadeNaoEncontradaException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}

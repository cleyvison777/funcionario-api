package com.funcionario.domain.empregado.repository.speciticaton.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FiltrosDto {
    private String nome;
    private String cpf;
    private int cargo;
}


package com.funcionario.domain.empregado.dto;

import com.funcionario.domain.cargo.dto.CargoDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpregadoDto {

    private Long id;
    private String nome;
    private int idade;
    private String cpf;
    private double salario;
    private CargoDto cargo;

}

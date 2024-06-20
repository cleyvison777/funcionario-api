package com.funcionario.domain.empregado.dto;

import com.funcionario.domain.cargo.dto.CargoDtoForm;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EmpregadoDtoForm {
    @NotBlank
    private String nome;
    @NotNull
    private Integer idade;
    @NotBlank
    private String cpf;
    @PositiveOrZero
    private double salario;
    @NotNull
    private boolean status;
    private EnderecoDtoForm endereco;
    @NotNull
    @Valid
    private CargoDtoForm cargo;


}

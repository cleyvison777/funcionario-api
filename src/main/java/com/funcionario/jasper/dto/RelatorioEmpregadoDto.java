package com.funcionario.jasper.dto;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class RelatorioEmpregadoDto {

    private int id;
    private String nome;
    private int idade;
    private String cpf;
    private Long salario;
    private String cargo;



}

package com.funcionario.domain.cargo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CargoDtoForm {
    @NotNull
    private Long id;
}

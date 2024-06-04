package com.funcionario.api.assembler;

import com.funcionario.domain.cargo.model.Cargo;
import com.funcionario.domain.empregado.dto.EmpregadoDtoForm;
import com.funcionario.domain.empregado.model.Empregado;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component

public class Assempler {
    private final ModelMapper modelMapper;

    public Assempler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public void copyToDomainObject(EmpregadoDtoForm empregadoDtoForm, Empregado empregado) {
            empregado.setCargo(new Cargo());
            modelMapper.map(empregadoDtoForm, empregado);
    }
}


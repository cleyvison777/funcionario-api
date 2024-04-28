package com.funcionario.api.controller;

import com.funcionario.domain.empregado.dto.EmpregadoDto;
import com.funcionario.domain.empregado.dto.EmpregadoDtoForm;
import com.funcionario.domain.empregado.model.Empregado;
import com.funcionario.domain.empregado.repository.speciticaton.dto.FiltrosDto;
import com.funcionario.domain.empregado.service.EmpregadoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("empregados")
public class EmpregadoController {
    private final EmpregadoService empregadoService;

    public EmpregadoController(EmpregadoService empregadoService) {
        this.empregadoService = empregadoService;
    }


    @GetMapping
    public ResponseEntity<Page<EmpregadoDto>> listarEmpregados(FiltrosDto filtrosDto, Pageable pageable) {
        return ResponseEntity.ok(empregadoService.buscarEmpregados(filtrosDto, pageable));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Empregado adicionar(@RequestBody @Valid EmpregadoDtoForm empregadoDtoForm) {
        return empregadoService.salvarEmpregado(empregadoDtoForm);
    }
}

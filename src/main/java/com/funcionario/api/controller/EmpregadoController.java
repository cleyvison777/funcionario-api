package com.funcionario.api.controller;

import com.funcionario.api.assembler.Assempler;
import com.funcionario.domain.empregado.dto.EmpregadoDto;
import com.funcionario.domain.empregado.dto.EmpregadoDtoForm;
import com.funcionario.domain.empregado.exception.EmpregadoNaoEncontradoException;
import com.funcionario.domain.empregado.model.Empregado;
import com.funcionario.domain.empregado.repository.speciticaton.dto.FiltrosDto;
import com.funcionario.domain.empregado.service.EmpregadoService;
import com.funcionario.domain.exception.NegocioException;
import com.funcionario.jasper.service.RelatorioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("empregados")
public class EmpregadoController {
    private final EmpregadoService empregadoService;
    private final RelatorioService relatorioService;
    private final Assempler assempler;

    public EmpregadoController(EmpregadoService empregadoService, Assempler assempler, RelatorioService relatorioService) {
        this.empregadoService = empregadoService;
        this.assempler = assempler;
        this.relatorioService = relatorioService;
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

    @DeleteMapping("/{empregadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void exclusaoLogica(@PathVariable Long empregadoId) {
        empregadoService.exclusaoLogica(empregadoId);
    }

    @Transactional
    @PutMapping("/{idEmpregado}")
    public Empregado atualizar(@PathVariable Long idEmpregado, @RequestBody @Valid EmpregadoDtoForm empregadoDtoForm) {
        Empregado empregadoAtual = empregadoService.buscarPorId(idEmpregado);
        assempler.copyToDomainObject(empregadoDtoForm, empregadoAtual);
        try {
            return empregadoService.atualizarEmpregado(empregadoAtual);
        } catch (EmpregadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @GetMapping("/relatorio/pdf")
    public ResponseEntity<byte[]> gerarRelatorioPDF(FiltrosDto filtrosDto) {
        try {
            byte[] relatorioBytes = relatorioService.gerarRelatorioPDF(filtrosDto);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "relatorio.pdf");
            headers.setContentLength(relatorioBytes.length);

            return new ResponseEntity<>(relatorioBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

package com.funcionario.jasper.service;


import com.funcionario.domain.empregado.dto.EmpregadoDto;
import com.funcionario.domain.empregado.repository.speciticaton.dto.FiltrosDto;
import com.funcionario.domain.empregado.service.EmpregadoService;
import com.funcionario.domain.exception.InternalServerErrorException;
import com.funcionario.jasper.dto.RelatorioEmpregadoDto;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class RelatorioService {
    private final ModelMapper modelMapper;
    private final EmpregadoService empregadoService;
    static final String SEPARADOR = File.separator;

    public static final String JASPER_DIRETORIO = "relatorios";
    public static final String JASPER_PREFIX = "Blank_A4.jasper";


    public RelatorioService(ModelMapper modelMapper, EmpregadoService empregadoService) {
        this.modelMapper = modelMapper;
        this.empregadoService = empregadoService;
    }


    public byte[] gerarRelatorioPDF(FiltrosDto filtrosDto) throws JRException {
        List<RelatorioEmpregadoDto> relatorioEmpregadoDtos = gerarRelatorio(filtrosDto);
        List<Map<String, Object>> data = getMaps(relatorioEmpregadoDtos);
        String jasperPath = JASPER_DIRETORIO + SEPARADOR + JASPER_PREFIX;
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
        JasperPrint jasperPrint = JasperFillManager.fillReport(this.obterPathArquivo(jasperPath), new HashMap<>(), dataSource);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        return outputStream.toByteArray();
    }

    private static List<Map<String, Object>> getMaps(List<RelatorioEmpregadoDto> relatorioEmpregadoDtos) {
        List<Map<String, Object>> data = new ArrayList<>();
        for (RelatorioEmpregadoDto dto : relatorioEmpregadoDtos) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", dto.getId());
            map.put("nome", dto.getNome());
            map.put("idade", dto.getIdade());
            map.put("cpf", dto.getCpf());
            map.put("salario", dto.getSalario());
            map.put("cargo", dto.getCargo().getNome());

            data.add(map);
        }
        return data;
    }

    public InputStream obterPathArquivo(String path) {
        return getClass().getClassLoader().getResourceAsStream(path);
    }

    List<RelatorioEmpregadoDto> gerarRelatorio(FiltrosDto filtrosDto) {
        try {
            Pageable pageable = Pageable.unpaged();
            Page<EmpregadoDto> dtoPageable = empregadoService.buscarEmpregados(filtrosDto, pageable);
            List<EmpregadoDto> empregadoDtos = dtoPageable.getContent();
            return empregadoDtos.stream().map(empregadoDto -> modelMapper.map(empregadoDto, RelatorioEmpregadoDto.class))
                    .toList();

        } catch (Exception e) {
            throw new InternalServerErrorException("Erro ao gerar o relatório", e.getMessage());
        }


    }
}

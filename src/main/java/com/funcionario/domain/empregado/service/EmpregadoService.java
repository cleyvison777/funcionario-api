package com.funcionario.domain.empregado.service;

import com.funcionario.client.endereco.dto.LocalidadesDto;
import com.funcionario.client.endereco.feignclient.LocalidadesClient;
import com.funcionario.domain.cargo.model.Cargo;
import com.funcionario.domain.cargo.service.CargoService;
import com.funcionario.domain.empregado.dto.EmpregadoDto;
import com.funcionario.domain.empregado.dto.EmpregadoDtoForm;
import com.funcionario.domain.empregado.exception.EmpregadoNaoEncontradoException;
import com.funcionario.domain.empregado.model.Empregado;
import com.funcionario.domain.empregado.repository.EmpregadoRepository;
import com.funcionario.domain.empregado.repository.speciticaton.EmpregadoSpecification;
import com.funcionario.domain.empregado.repository.speciticaton.dto.FiltrosDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.funcionario.client.endereco.dto.LocalidadesDto.cadastrarLocalidade;

@Service
public class EmpregadoService {
    public static final boolean INATIVO = false;
    private final EmpregadoRepository empregadoRepository;
    private final ModelMapper modelMapper;
    private final CargoService cargoService;
    private final LocalidadesClient localidadesClient;
    public EmpregadoService(EmpregadoRepository empregadoRepository, ModelMapper modelMapper, CargoService cargoService, LocalidadesClient localidadesClient) {
        this.empregadoRepository = empregadoRepository;
        this.modelMapper = modelMapper;
        this.cargoService = cargoService;
        this.localidadesClient = localidadesClient;
    }

    public Empregado salvarEmpregado(EmpregadoDtoForm empregadoDtoForm) {
        Empregado empregado = modelMapper.map(empregadoDtoForm, Empregado.class);
        Long cargoId = empregadoDtoForm.getCargo().getId();
        Cargo cargo = cargoService.buscarCargoPorId(cargoId);
        LocalidadesDto clientEnderecoDto = localidadesClient.getEnderecoDto(empregadoDtoForm.getEndereco().getCep());
        cadastrarLocalidade(empregado, clientEnderecoDto);
        empregado.setCargo(cargo);
        return empregadoRepository.save(empregado);
    }



    @Transactional
    public void exclusaoLogica(Long empregadoId) {
        Empregado empregado = empregadoRepository.findById(empregadoId)
                .orElseThrow(() -> new EmpregadoNaoEncontradoException(empregadoId));

        empregado.setStatus(INATIVO);
        empregadoRepository.save(empregado);
    }


    public Page<EmpregadoDto> buscarEmpregados(FiltrosDto filtrosDto, Pageable pageable) {
        Specification<Empregado> specification = EmpregadoSpecification.buscarEmpregadosFiltros(filtrosDto);
        Page<Empregado> empregados = empregadoRepository.findAll(specification, pageable);
        return empregados.map(empregado -> modelMapper.map(empregado, EmpregadoDto.class));
    }
}

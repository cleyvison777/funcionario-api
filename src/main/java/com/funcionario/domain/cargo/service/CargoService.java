package com.funcionario.domain.cargo.service;

import com.funcionario.domain.cargo.exception.CargoNaoEncontradoException;
import com.funcionario.domain.cargo.model.Cargo;
import com.funcionario.domain.cargo.repository.CargoRepository;
import org.springframework.stereotype.Service;

@Service
public class CargoService {
    private  final CargoRepository cargoRepository;

    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public Cargo buscarCargoPorId(Long cargoId) {
        return cargoRepository.findById(cargoId).orElseThrow(() -> new CargoNaoEncontradoException(cargoId));
    }
}

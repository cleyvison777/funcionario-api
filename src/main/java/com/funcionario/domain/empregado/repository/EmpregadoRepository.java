package com.funcionario.domain.empregado.repository;


import com.funcionario.domain.empregado.model.Empregado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpregadoRepository extends JpaRepository<Empregado, Long>, JpaSpecificationExecutor<Empregado> {
}

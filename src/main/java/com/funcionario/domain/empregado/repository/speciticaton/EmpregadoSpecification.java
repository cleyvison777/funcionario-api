package com.funcionario.domain.empregado.repository.speciticaton;

import com.funcionario.domain.empregado.model.Empregado;
import com.funcionario.domain.empregado.repository.speciticaton.dto.FiltrosDto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class EmpregadoSpecification {
    private EmpregadoSpecification() {
    }

    public static Specification<Empregado> buscarEmpregadosFiltros(FiltrosDto filtros) {
        List<Predicate> predicates = new ArrayList<>();
        return (root, query, criteriaBuilder) -> {
            if (filtros.getNome() != null && !filtros.getNome().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("nome"), filtros.getNome()));
            }
            if (filtros.getCpf() != null && !filtros.getCpf().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("cpf"), filtros.getCpf()));
            }
            if (filtros.getStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), filtros.getStatus()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

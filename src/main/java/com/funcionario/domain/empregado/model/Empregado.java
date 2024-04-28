package com.funcionario.domain.empregado.model;

import com.funcionario.domain.cargo.model.Cargo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "FUNCIONARIO")
public class Empregado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int idade;
    private String cpf;
    private double salario;

    @ManyToOne
    @JoinColumn(name = "id_cargo")
    private Cargo cargo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empregado empregado = (Empregado) o;
        return Objects.equals(id, empregado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

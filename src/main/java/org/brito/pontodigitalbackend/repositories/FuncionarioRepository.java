package org.brito.pontodigitalbackend.repositories;

import org.brito.pontodigitalbackend.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}

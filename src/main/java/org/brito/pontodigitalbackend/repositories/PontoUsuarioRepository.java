package org.brito.pontodigitalbackend.repositories;

import org.brito.pontodigitalbackend.domain.PontoUsuario;
import org.brito.pontodigitalbackend.domain.pk.PontoUsuarioPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PontoUsuarioRepository extends JpaRepository<PontoUsuario, PontoUsuarioPK> {

    @Query("SELECT pu FROM Ponto pu WHERE pu.id.usuarioId = :usuarioId")
    List<PontoUsuario> buscarPorUsuarioId(@Param("usuarioId") Long usuarioId);

}

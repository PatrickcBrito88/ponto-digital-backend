package org.brito.pontodigitalbackend.services;

import org.brito.pontodigitalbackend.dtos.PontoUsuarioDTO;
import org.brito.pontodigitalbackend.dtos.PontoUsuarioRegistroDTO;

import java.util.List;

public interface PontoUsuarioService {

    String salvarPontoUsuario(PontoUsuarioRegistroDTO pontoUsuarioRegistroDTO);

    PontoUsuarioDTO buscarPontoUsuarioPorId(Long idUsuario);

    List<PontoUsuarioDTO> buscaTodosPontos();
}

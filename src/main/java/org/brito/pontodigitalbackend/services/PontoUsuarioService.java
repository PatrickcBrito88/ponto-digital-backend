package org.brito.pontodigitalbackend.services;

import org.brito.pontodigitalbackend.dtos.PontoUsuarioDTO;
import org.brito.pontodigitalbackend.dtos.PontoUsuarioRegistroDTO;

public interface PontoUsuarioService {

    String salvarPontoUsuario(PontoUsuarioRegistroDTO pontoUsuarioRegistroDTO);

    PontoUsuarioDTO buscarPontoUsuario(Long idUsuario);
}

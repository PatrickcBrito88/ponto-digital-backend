package org.brito.pontodigitalbackend.services;

import org.brito.pontodigitalbackend.dtos.JustificativaUsuarioDTO;
import org.brito.pontodigitalbackend.dtos.PontoUsuarioDTO;
import org.brito.pontodigitalbackend.dtos.PontoUsuarioRegistroDTO;
import org.springframework.data.domain.Page;

public interface PontoUsuarioService {

    String salvarPontoUsuario(PontoUsuarioRegistroDTO pontoUsuarioRegistroDTO);

    PontoUsuarioDTO buscarPontoUsuarioPorId(Long idUsuario);

    Page<PontoUsuarioDTO> buscaTodosPontos(Integer paginaAtual, Integer tamanhoPagina);

    String salvarJutificativaUsuario(JustificativaUsuarioDTO justificativaUsuarioDTO);
}

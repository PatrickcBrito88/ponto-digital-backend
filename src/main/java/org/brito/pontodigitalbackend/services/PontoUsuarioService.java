package org.brito.pontodigitalbackend.services;

import org.brito.pontodigitalbackend.dtos.JustificativaUsuarioDTO;
import org.brito.pontodigitalbackend.dtos.PontoUsuarioDTO;
import org.brito.pontodigitalbackend.dtos.PontoUsuarioRegistroDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

public interface PontoUsuarioService {

    String salvarPontoUsuario(PontoUsuarioRegistroDTO pontoUsuarioRegistroDTO);

    PontoUsuarioDTO buscarPontoUsuarioPorId(Long idUsuario);

    Page<PontoUsuarioDTO> buscaTodosPontos(Integer paginaAtual, Integer tamanhoPagina);

    String salvarJutificativaUsuario(JustificativaUsuarioDTO justificativaUsuarioDTO);

    String uploadAnexo(MultipartFile file, String idFuncionario, LocalDate data) throws IOException;

    String downloadAnexo(String nomeArquivo, String idFuncionario, LocalDate data) throws IOException;

    String apagarAnexo(String nomeArquivo, String idFuncionario, LocalDate data);
}

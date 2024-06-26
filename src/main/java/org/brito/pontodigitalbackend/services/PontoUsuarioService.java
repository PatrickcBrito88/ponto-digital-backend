package org.brito.pontodigitalbackend.services;

import org.brito.pontodigitalbackend.domain.PontoUsuario;
import org.brito.pontodigitalbackend.dtos.*;
import org.brito.pontodigitalbackend.enums.EStatusPonto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

public interface PontoUsuarioService {

    String salvarPontoUsuario(PontoUsuarioRegistroDTO pontoUsuarioRegistroDTO);

    PontoUsuarioDTO buscarPontoUsuarioPorId(Long idUsuario);

    Page<PontoUsuarioDTO> buscaTodosPontos(Integer paginaAtual, Integer tamanhoPagina);

    JustificativaDTO salvarJutificativaUsuario(JustificativaUsuarioDTO justificativaUsuarioDTO);

    String uploadAnexo(MultipartFile file, String idFuncionario, LocalDate data) throws IOException;

    String downloadAnexo(String nomeArquivo, String idFuncionario, LocalDate data) throws IOException;

    String apagarAnexo(String nomeArquivo, String idFuncionario, LocalDate data);

    String aprovarPonto(LocalDate data, String idFuncionario, EStatusPonto situacao);

    String ajustePontoEmpregador(HorariosAlteracaoDTO horariosAlteracaoDTO);

    String confirmaAlteracaoPontoFuncionario(LocalDate data, String idFuncionario);

    String solicitarAjustePonto(JustificativaUsuarioDTO justificativaUsuarioDTO);

    PontoUsuario validarPonto(LocalDate data, String idFuncionario);
}

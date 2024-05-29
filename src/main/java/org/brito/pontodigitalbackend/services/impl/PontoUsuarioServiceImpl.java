package org.brito.pontodigitalbackend.services.impl;

import org.brito.pontodigitalbackend.domain.PontoUsuario;
import org.brito.pontodigitalbackend.domain.pk.PontoUsuarioPK;
import org.brito.pontodigitalbackend.dtos.JustificativaUsuarioDTO;
import org.brito.pontodigitalbackend.dtos.PontoDTO;
import org.brito.pontodigitalbackend.dtos.PontoUsuarioDTO;
import org.brito.pontodigitalbackend.dtos.PontoUsuarioRegistroDTO;
import org.brito.pontodigitalbackend.repositories.PontoUsuarioRepository;
import org.brito.pontodigitalbackend.repositories.UsuarioRepository;
import org.brito.pontodigitalbackend.services.PontoUsuarioService;
import org.brito.pontodigitalbackend.services.UsuarioService;
import org.brito.pontodigitalbackend.utils.Paginador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PontoUsuarioServiceImpl implements PontoUsuarioService {

    @Autowired
    PontoUsuarioRepository pontoUsuarioRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioService usuarioService;


    @Override
    public String salvarPontoUsuario(PontoUsuarioRegistroDTO pontoUsuarioRegistroDTO) {
        PontoUsuarioPK pontoUsuarioPK =
                new PontoUsuarioPK(
                        Long.parseLong(pontoUsuarioRegistroDTO.getIdUsuario()),
                        pontoUsuarioRegistroDTO.getPonto().getData());
        PontoUsuario pontoUsuario = new PontoUsuario(
                pontoUsuarioPK,
                pontoUsuarioRegistroDTO.getPonto().getEntrada(),
                pontoUsuarioRegistroDTO.getPonto().getInicioAlmoco(),
                pontoUsuarioRegistroDTO.getPonto().getFimAlmoco(),
                pontoUsuarioRegistroDTO.getPonto().getSaida(),
                false,
                "");

        pontoUsuarioRepository.save(pontoUsuario);
        return "OK";
    }

    @Override
    public PontoUsuarioDTO buscarPontoUsuarioPorId(Long idUsuario) {
        List<PontoUsuario> lista = pontoUsuarioRepository.buscarPorUsuarioId(idUsuario);
        return gerarPontoUsuario(idUsuario, lista);
    }

    private static PontoUsuarioDTO gerarPontoUsuario(Long idUsuario, List<PontoUsuario> lista) {
        List<PontoDTO> listaPonto =
                lista.stream()
                        .map(item -> new PontoDTO(
                                item.getId().getData(),
                                item.getEntrada(),
                                item.getInicioAlmoco(),
                                item.getFimAlmoco(),
                                item.getSaida(),
                                item.getJustificativa()))
                        .collect(Collectors.toList());

        return new PontoUsuarioDTO(idUsuario, listaPonto);
    }

    @Override
    public Page<PontoUsuarioDTO> buscaTodosPontos(Integer paginaAtual, Integer tamanhoPagina) {
        List<PontoUsuario> lista = pontoUsuarioRepository.findAll();

        List<PontoUsuarioDTO> listaPontoUsuarioDTO = new ArrayList<>(lista.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getId().getUsuarioId(),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                pl -> {
                                    PontoUsuarioDTO dto = new PontoUsuarioDTO();
                                    dto.setIdUsuario(pl.get(0).getId().getUsuarioId());
                                    List<PontoDTO> pontoDTOs = pl.stream().map(p -> {
                                        return new PontoDTO(
                                                p.getId().getData(),
                                                p.getEntrada(),
                                                p.getInicioAlmoco(),
                                                p.getFimAlmoco(),
                                                p.getSaida(),
                                                p.getJustificativa());
                                    }).collect(Collectors.toList());
                                    dto.setPonto(pontoDTOs);
                                    return dto;
                                }
                        )
                ))
                .values());

        List<PontoUsuarioDTO> listaPontoUsuario = listaPontoUsuarioDTO.stream()
                .peek(u -> {
                    String nomeCompleto = usuarioService.buscarPeloId(u.getIdUsuario()).getNome();
                    u.setNomeCompleto(nomeCompleto);
                })
                .collect(Collectors.toList());

        return Paginador.gerarPaginacao(listaPontoUsuario, paginaAtual, tamanhoPagina);
    }

    @Override
    public String salvarJutificativaUsuario(JustificativaUsuarioDTO justificativaUsuarioDTO) {
        Long idUsuario = Long.valueOf(justificativaUsuarioDTO.getIdUsuario());
        LocalDate data = justificativaUsuarioDTO.getData();
        PontoUsuario pontoUsuario = pontoUsuarioRepository.buscarPorUsuarioEData(idUsuario, data);
        pontoUsuario.setJustificativa(justificativaUsuarioDTO.getJustificativa());

        pontoUsuarioRepository.save(pontoUsuario);

        return "Justificativa guardada com sucesso";
    }



}

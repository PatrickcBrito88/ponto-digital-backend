package org.brito.pontodigitalbackend.services.impl;

import org.brito.pontodigitalbackend.domain.PontoUsuario;
import org.brito.pontodigitalbackend.domain.pk.PontoUsuarioPK;
import org.brito.pontodigitalbackend.dtos.PontoDTO;
import org.brito.pontodigitalbackend.dtos.PontoUsuarioDTO;
import org.brito.pontodigitalbackend.dtos.PontoUsuarioRegistroDTO;
import org.brito.pontodigitalbackend.repositories.PontoUsuarioRepository;
import org.brito.pontodigitalbackend.repositories.UsuarioRepository;
import org.brito.pontodigitalbackend.services.PontoUsuarioService;
import org.brito.pontodigitalbackend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                pontoUsuarioRegistroDTO.getPonto().getSaida());

        pontoUsuarioRepository.save(pontoUsuario);
        return "OK";
    }

    @Override
    public PontoUsuarioDTO buscarPontoUsuarioPorId(Long idUsuario) {
        List<PontoUsuario> lista = pontoUsuarioRepository.buscarPorUsuarioId(idUsuario);
        List<PontoDTO> listaPonto =
                lista.stream()
                        .map(item -> new PontoDTO(
                                item.getId().getData(),
                                item.getEntrada(),
                                item.getInicioAlmoco(),
                                item.getFimAlmoco(),
                                item.getSaida()))
                        .collect(Collectors.toList());

        return new PontoUsuarioDTO(idUsuario, listaPonto);
    }

    @Override
    public List<PontoUsuarioDTO> buscaTodosPontos() {
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
                                                p.getSaida());
                                    }).collect(Collectors.toList());
                                    dto.setPonto(pontoDTOs);
                                    return dto;
                                }
                        )
                ))
                .values());

        return listaPontoUsuarioDTO.stream()
                .peek(u -> {
                    String nomeCompleto = usuarioService.buscarPeloId(u.getIdUsuario()).getNome();
                    u.setNomeCompleto(nomeCompleto);
                })
                .collect(Collectors.toList());
    }

}

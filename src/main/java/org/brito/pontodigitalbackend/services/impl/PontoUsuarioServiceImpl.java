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
    public PontoUsuarioDTO buscarPontoUsuario(Long idUsuario) {
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

}

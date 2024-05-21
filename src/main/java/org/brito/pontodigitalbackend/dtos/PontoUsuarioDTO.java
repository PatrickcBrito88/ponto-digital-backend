package org.brito.pontodigitalbackend.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class PontoUsuarioDTO {

    private Long idUsuario;
    private String nomeCompleto;
    private List<PontoDTO> ponto;

    public PontoUsuarioDTO(Long idUsuario, String nomeCompleto, List<PontoDTO> ponto) {
        this.idUsuario = idUsuario;
        this.nomeCompleto = nomeCompleto;
        this.ponto = ponto;
    }

    public PontoUsuarioDTO(Long idUsuario, List<PontoDTO> ponto) {
        this.idUsuario = idUsuario;
        this.nomeCompleto = nomeCompleto;
        this.ponto = ponto;
    }
}

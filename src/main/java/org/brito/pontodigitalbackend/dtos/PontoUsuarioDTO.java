package org.brito.pontodigitalbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class PontoUsuarioDTO {

    private Long idUsuario;
    private List<PontoDTO> ponto;
}

package org.brito.pontodigitalbackend.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class JustificativaUsuarioDTO {

    private String idUsuario;
    private LocalDate data;
    private String justificativa;
}

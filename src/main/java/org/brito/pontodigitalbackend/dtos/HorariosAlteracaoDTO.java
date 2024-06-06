package org.brito.pontodigitalbackend.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class HorariosAlteracaoDTO {

    private String idFuncionario;
    private LocalDate data;
    private LocalTime entrada;
    private LocalTime inicioAlmoco;
    private LocalTime fimAlmoco;
    private LocalTime saida;
    private String justificativaEmpregador;

}

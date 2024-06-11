package org.brito.pontodigitalbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.brito.pontodigitalbackend.domain.HorarioAlterado;
import org.brito.pontodigitalbackend.domain.Justificativa;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class PontoDTO {

    private LocalDate data;
    private LocalTime entrada;
    private LocalTime inicioAlmoco;
    private LocalTime fimAlmoco;
    private LocalTime saida;
    private List<Justificativa> justificativa;
    private List<String> anexos;
    private String situacao;
    private List<HorarioAlterado> horariosAlterados;

}

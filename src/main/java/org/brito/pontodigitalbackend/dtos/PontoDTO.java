package org.brito.pontodigitalbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Setter
@Getter
public class PontoDTO {

    private LocalDate data;
    private LocalTime entrada;
    private LocalTime inicioAlmoco;
    private LocalTime fimAlmoco;
    private LocalTime saida;

}

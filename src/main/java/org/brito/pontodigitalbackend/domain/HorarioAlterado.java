package org.brito.pontodigitalbackend.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;


@Getter
@Setter
@Embeddable
public class HorarioAlterado {

    private String periodo;
    private LocalTime horarioInicial;
    private LocalTime horarioAjustado;
    private String dataHoraAlteracao;

}

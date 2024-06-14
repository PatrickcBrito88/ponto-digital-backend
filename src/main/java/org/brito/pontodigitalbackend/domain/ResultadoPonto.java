package org.brito.pontodigitalbackend.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.brito.pontodigitalbackend.enums.EAbonaPonto;
import org.brito.pontodigitalbackend.enums.ECompensaPonto;

@Getter
@Setter
@Embeddable
public class ResultadoPonto {

    private double minutos;
    private EAbonaPonto abono;
    private ECompensaPonto compensacao;

}

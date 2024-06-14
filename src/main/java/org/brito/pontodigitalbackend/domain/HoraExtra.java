package org.brito.pontodigitalbackend.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HoraExtra {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "minutos", column = @Column(name = "minutos_hora_extra_entrada")),
            @AttributeOverride(name = "abono", column = @Column(name = "abono_hora_extra_entrada")),
            @AttributeOverride(name = "compensacao", column = @Column(name = "compensacao_hora_extra_entrada"))
    })
    private ResultadoPonto horaExtraEntrada;
    

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "minutos", column = @Column(name = "minutos_hora_extra_saida_antecipada")),
            @AttributeOverride(name = "abono", column = @Column(name = "abono_hora_extra_saida_antecipada")),
            @AttributeOverride(name = "compensacao", column = @Column(name = "compensacao_hora_extra_saida_antecipada"))
    })
    private ResultadoPonto horaExtraSaida;

    public HoraExtra(ResultadoPonto horaExtraEntrada, ResultadoPonto horaExtraSaida) {
        this.horaExtraEntrada = horaExtraEntrada;
        this.horaExtraSaida = horaExtraSaida;
    }
}

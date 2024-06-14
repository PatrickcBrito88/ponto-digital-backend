package org.brito.pontodigitalbackend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class Atraso {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "minutos", column = @Column(name = "minutos_atraso_entrada")),
            @AttributeOverride(name = "abono", column = @Column(name = "abono_atraso_entrada")),
            @AttributeOverride(name = "compensacao", column = @Column(name = "compensacao_atraso_entrada"))
    })
    private ResultadoPonto atrasoEntrada;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "minutos", column = @Column(name = "minutos_atraso_almoco")),
            @AttributeOverride(name = "abono", column = @Column(name = "abono_atraso_almoco")),
            @AttributeOverride(name = "compensacao", column = @Column(name = "compensacao_atraso_almoco"))
    })
    private ResultadoPonto atrasoAlmoco;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "minutos", column = @Column(name = "minutos_atraso_saida_antecipada")),
            @AttributeOverride(name = "abono", column = @Column(name = "abono_atraso_saida_antecipada")),
            @AttributeOverride(name = "compensacao", column = @Column(name = "compensacao_atraso_saida_antecipada"))
    })
    private ResultadoPonto saidaAntecipada;

    public Atraso(ResultadoPonto atrasoEntrada, ResultadoPonto atrasoAlmoco, ResultadoPonto saidaAntecipada) {
        this.atrasoEntrada = atrasoEntrada;
        this.atrasoAlmoco = atrasoAlmoco;
        this.saidaAntecipada = saidaAntecipada;
    }


}

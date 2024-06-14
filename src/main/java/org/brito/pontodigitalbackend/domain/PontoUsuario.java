package org.brito.pontodigitalbackend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.brito.pontodigitalbackend.domain.pk.PontoUsuarioPK;

import java.time.LocalTime;
import java.util.List;

@Entity(name = "Ponto")
@Table(name = "Ponto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PontoUsuario {

    @EmbeddedId
    private PontoUsuarioPK id;

    private LocalTime entrada;

    private LocalTime inicioAlmoco;

    private LocalTime fimAlmoco;

    private LocalTime saida;

    private String situacao;

    @ElementCollection
    @CollectionTable(name = "ponto_justificativas")
    @Column(name = "justificativa")
    private List<Justificativa> justificativas;

    @ElementCollection
    @CollectionTable(name = "ponto_anexos")
    @Column(name = "anexo")
    private List<String> anexos;

    @ElementCollection
    @CollectionTable(name = "horarios_alterados")
    @Column(name = "horarios_alterados")
    private List<HorarioAlterado> horariosAlterados;

    private Boolean cienciaFuncionarioPontoAlterado;

    private String dataHoraCienciaFuncionarioPontoAlterado;

    @Embedded
    private Atraso atrasos;

    @Embedded
    private HoraExtra horasExtras;




    public PontoUsuario(PontoUsuarioPK id, LocalTime entrada,
                        LocalTime inicioAlmoco, LocalTime fimAlmoco,
                        LocalTime saida, List<Justificativa> justificativas,
                        String situacao, Atraso atrasos, HoraExtra horasExtras) {
        this.id = id;
        this.entrada = entrada;
        this.inicioAlmoco = inicioAlmoco;
        this.fimAlmoco = fimAlmoco;
        this.saida = saida;
        this.justificativas = justificativas;
        this.situacao = situacao;
        this.atrasos = atrasos;
        this.horasExtras = horasExtras;
    }
}

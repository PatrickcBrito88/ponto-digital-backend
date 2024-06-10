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
    private String justificativa;
    @ElementCollection
    @CollectionTable(name = "ponto_anexos")
    @Column(name = "anexo")
    private List<String> anexos;
    @ElementCollection
    @CollectionTable(name = "horarios_alterados")
    @Column(name = "horarios_alterados")
    private List<HorarioAlterado> horariosAlterados;

    public PontoUsuario(PontoUsuarioPK id, LocalTime entrada,
                        LocalTime inicioAlmoco, LocalTime fimAlmoco,
                        LocalTime saida, String justificativa,
                        String situacao) {
        this.id = id;
        this.entrada = entrada;
        this.inicioAlmoco = inicioAlmoco;
        this.fimAlmoco = fimAlmoco;
        this.saida = saida;
        this.justificativa = justificativa;
        this.situacao = situacao;
    }
}

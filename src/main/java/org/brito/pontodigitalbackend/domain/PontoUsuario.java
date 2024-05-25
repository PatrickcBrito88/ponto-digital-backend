package org.brito.pontodigitalbackend.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.brito.pontodigitalbackend.domain.pk.PontoUsuarioPK;

import java.time.LocalTime;

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
    private boolean isAprovado;
    private String justificativa;


}

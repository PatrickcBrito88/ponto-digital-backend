package org.brito.pontodigitalbackend.domain.pk;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PontoUsuarioPK implements Serializable {

    private Long usuarioId;
    private LocalDate data;

}


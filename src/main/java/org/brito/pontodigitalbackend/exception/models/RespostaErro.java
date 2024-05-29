package org.brito.pontodigitalbackend.exception.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RespostaErro {

    private LocalDateTime timestamp;
    private String mensagem;
    private String detalhes;
    private HttpStatus status;

    public RespostaErro(LocalDateTime timestamp, String mensagem, String detalhes, HttpStatus status) {
        this.timestamp = timestamp;
        this.mensagem = mensagem;
        this.detalhes = detalhes;
        this.status = status;
    }

}

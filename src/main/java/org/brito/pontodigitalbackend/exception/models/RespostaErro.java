package org.brito.pontodigitalbackend.exception.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RespostaErro {

    private int status;
    private BodyErro body;


    public RespostaErro(BodyErro body, int status) {
        this.body = body;
        this.status = status;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BodyErro {
        private LocalDateTime timestamp;
        private String mensagem;
        private String detalhes;
    }

}

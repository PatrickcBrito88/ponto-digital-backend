package org.brito.pontodigitalbackend.enums;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

public enum CodigoHttp {
    MOVED_PERMANENTLY(301, "ALERTA_REQUISICAO_MOVIDA", "Requisição movida permanentemente"),
    BAD_REQUEST(400, "ERRO_REQUISICAO_ERRADA", "Requisição fora do padrão esperado"),
    UNAUTHORIZED(401, "ERRO_REQUISICAO_NAO_AUTORIZADA", "Usuário não autenticado"),
    FORBIDDEN(403, "ERRO_REQUISICAO_PROIBIDA", "Usuário não tem acesso a esse conteúdo"),
    NOT_FOUND(404, "ERRO_REQUISICAO_NAO_ENCONTRADO", "Conteúdo não encontrado"),
    METHOD_NOT_ALLOWED(405, "ERRO_METODO_NAO_PERMITIDO", "Método não permitido"),
    REQUEST_TIMEOUT(408, "ERRO_REQUISICAO_DEMOROU", "Requisição demorou muito"),
    PAYLOAD_TOO_LARGE(413, "ERRO_REQUISICAO_MUITO_GRANDE", "Requisição muito grande"),
    URI_TOO_LONG(414, "ERRO_REQUISICAO_MUITO_GRANDE", "Requisição muito grande"),
    TOO_MANY_REQUESTS(429, "ERRO_MUITAS_REQUISICOES_SEGUIDAS", "Número de tentativas excedido"),
    INTERNAL_SERVER_ERROR(500, "ERRO_SERVIDOR", "Ops, aconteceu um erro!"),
    NOT_IMPLEMENTED(501, "ERRO_SERVIDOR_METODO_INEXISTENTE", "Ops, aconteceu um erro!"),
    BAD_GATEWAY(502, "ERRO_SERVIDOR_GATEWAY", "Ops, aconteceu um erro!"),
    SERVICE_UNAVAILABLE(503, "ERRO_SERVIDOR_INDISPONIVEL", "Serviço temporariamente indisponível! Por favor, tente mais tarde."),
    GATEWAY_TIMEOUT(504, "ERRO_SERVIDOR_GATEWAY_TIMEOUT", "Ops, aconteceu um erro!");

    private final int value;
    private final String codigo;
    private final String detalhe;

    CodigoHttp(int value, String codigo, String detalhe) {
        this.value = value;
        this.codigo = codigo;
        this.detalhe = detalhe;
    }

    public int getValue() {
        return this.value;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public String getDetalhe() {
        return this.detalhe;
    }

    public static CodigoHttp valueOf(int value) {
        return Arrays.stream(values())
                .filter(v -> v.value == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No matching constant for [" + value + "]"));
    }
}
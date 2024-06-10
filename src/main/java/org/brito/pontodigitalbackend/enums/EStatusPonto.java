package org.brito.pontodigitalbackend.enums;

public enum EStatusPonto {
    PENDENTE_EMPREGADOR("pendente_empregador"),
    PENDENTE_FUNCIONARIO("pendente_funcionario"),
    APROVADO("aprovado");

    private final String status;

    EStatusPonto(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
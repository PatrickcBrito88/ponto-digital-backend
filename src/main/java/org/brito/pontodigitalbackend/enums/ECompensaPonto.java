package org.brito.pontodigitalbackend.enums;

public enum ECompensaPonto {
    BANCO_HORAS("BANCO_HORAS"),
    PAGAMENTO("PAGAMENTO");

    private final String status;

    ECompensaPonto(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
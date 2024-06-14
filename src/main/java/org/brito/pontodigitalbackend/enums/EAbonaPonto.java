package org.brito.pontodigitalbackend.enums;

public enum EAbonaPonto {
    ABONADO("ABONADO"),
    NAO_ABONADO("NAO_ABONADO");

    private final String status;

    EAbonaPonto(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
package org.brito.pontodigitalbackend.exception.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class EntityErrorResponse {

    private String id;
    private String status;
    private String codigo;
    private String titulo;
    private Map<String, Object> detalhe;
    private Vinculos vinculos;
    private Map<String, Object> metaDado;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Vinculos {
        private String sobre;
    }

}
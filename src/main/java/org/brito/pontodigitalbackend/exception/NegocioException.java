package org.brito.pontodigitalbackend.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

import static org.brito.pontodigitalbackend.utils.Constants.RESPONSE_400;


public class NegocioException extends ApplicationException {

    private static final long serialVersionUID = 1L;

    private static final HttpStatus statusCode = HttpStatus.BAD_REQUEST;

    /**
     * Construtor que já possui uma mensagem padrão.
     *
     */
    public NegocioException() {
        getErro().setMensagem(RESPONSE_400);
        getErro().setStatusCode(statusCode);
    }

    public NegocioException(final String message) {
        super(message, statusCode);
    }
    public NegocioException(final String message, final Map<String, Object> metaDado) {
        super(message, statusCode, metaDado);
    }

}

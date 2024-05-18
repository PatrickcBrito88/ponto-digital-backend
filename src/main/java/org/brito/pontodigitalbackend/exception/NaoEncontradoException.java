package org.brito.pontodigitalbackend.exception;

import org.brito.pontodigitalbackend.utils.Constants;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class NaoEncontradoException extends ApplicationException {

    private static final long serialVersionUID = 1L;
    private static final HttpStatus statusCode = HttpStatus.NOT_FOUND;

    public NaoEncontradoException() {
        getErro().setMensagem(Constants.RESPONSE_404);
        getErro().setStatusCode(statusCode);
    }

    public NaoEncontradoException(final String message) {
        super(message, statusCode);
    }

    public NaoEncontradoException(final String message, final Map<String, Object> metaDado) {
        super(message, statusCode, metaDado);
    }

}

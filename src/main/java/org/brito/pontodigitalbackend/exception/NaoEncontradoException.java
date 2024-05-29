package org.brito.pontodigitalbackend.exception;

import org.springframework.http.HttpStatus;

public class NaoEncontradoException extends HandlerException {

    private static final long serialVersionUID = 1L;
    private static final HttpStatus statusCode = HttpStatus.NOT_FOUND;

    public NaoEncontradoException(String mensagem) {
        super(mensagem, statusCode);
    }

}

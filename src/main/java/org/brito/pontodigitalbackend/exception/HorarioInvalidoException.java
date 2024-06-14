package org.brito.pontodigitalbackend.exception;

import org.springframework.http.HttpStatus;

public class HorarioInvalidoException extends HandlerException {

    private static final long serialVersionUID = 1L;
    private static final HttpStatus statusCode = HttpStatus.BAD_REQUEST;

    public HorarioInvalidoException(String mensagem) {
        super(mensagem, statusCode);
    }

}

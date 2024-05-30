package org.brito.pontodigitalbackend.exception;

import org.springframework.http.HttpStatus;

public class EmailException extends HandlerException {

    private static final long serialVersionUID = 1L;
    private static final HttpStatus statusCode = HttpStatus.BAD_REQUEST;

    public EmailException(String mensagem) {
        super(mensagem, statusCode);
    }

}

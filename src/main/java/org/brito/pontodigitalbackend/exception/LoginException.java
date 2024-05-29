package org.brito.pontodigitalbackend.exception;

import org.springframework.http.HttpStatus;

public class LoginException extends HandlerException {

    private static final long serialVersionUID = 1L;
    private static final HttpStatus statusCode = HttpStatus.BAD_REQUEST;

    public LoginException(String mensagem) {
        super(mensagem, statusCode);
    }

}

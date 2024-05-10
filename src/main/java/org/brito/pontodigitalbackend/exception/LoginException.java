package org.brito.pontodigitalbackend.exception;

import org.brito.pontodigitalbackend.utils.Constants;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class LoginException extends ApplicationException {

    private static final long serialVersionUID = 1L;
    private static final HttpStatus statusCode = HttpStatus.BAD_REQUEST;

    public LoginException() {
        getErro().setMensagem(Constants.RESPONSE_400);
        getErro().setStatusCode(statusCode);
    }

    public LoginException(final String message) {
        super(message, statusCode);
    }

    public LoginException(final String message, final Map<String, Object> metaDado) {
        super(message, statusCode, metaDado);
    }

}

package org.brito.pontodigitalbackend.exception;

import org.springframework.http.HttpStatus;

public class HandlerException extends ApplicationException {


    public HandlerException(String mensagemErro, HttpStatus statusCode) {
        getErro().setMensagem(mensagemErro);
        getErro().setStatusCode(statusCode);
    }

}

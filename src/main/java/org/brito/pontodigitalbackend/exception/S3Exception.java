package org.brito.pontodigitalbackend.exception;

import org.brito.pontodigitalbackend.utils.Constants;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class S3Exception extends ApplicationException {

    private static final long serialVersionUID = 1L;
    private static final HttpStatus statusCode = HttpStatus.BAD_REQUEST;

    public S3Exception() {
        getErro().setMensagem(Constants.RESPONSE_400);
        getErro().setStatusCode(statusCode);
    }

    public S3Exception(final String message) {
        super(message, statusCode);
    }

    public S3Exception(final String message, final Map<String, Object> metaDado) {
        super(message, statusCode, metaDado);
    }

}

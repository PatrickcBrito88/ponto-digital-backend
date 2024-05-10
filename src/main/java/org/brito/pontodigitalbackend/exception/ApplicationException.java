package org.brito.pontodigitalbackend.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.brito.pontodigitalbackend.domain.DefaultError;
import org.springframework.http.HttpStatus;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final transient DefaultError erro;

    public ApplicationException() {
        super();
        erro = new DefaultError();
    }

    public ApplicationException(final String mensagem, final HttpStatus status) {
        super();
        erro = new DefaultError(mensagem, status);
    }

    public ApplicationException(final String mensagem, final HttpStatus status, final Map<String, Object> metaDado) {
        super();
        erro = new DefaultError(mensagem, status, metaDado);
    }

    public ApplicationException(final String mensagem) {
        this(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public DefaultError getErro() {
        return erro;
    }

    @Override
    public String toString() {
        return String.format("ExemploException [erro=%s]", erro);
    }
}

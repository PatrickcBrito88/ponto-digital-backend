package org.brito.pontodigitalbackend.handler;

import org.brito.pontodigitalbackend.exception.*;
import org.brito.pontodigitalbackend.exception.models.RespostaErro;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<RespostaErro> handleNaoEncontradoException(NaoEncontradoException ex, WebRequest request) {
        RespostaErro respostaErro = getRespostaErro(ex, request);
        return new ResponseEntity<>(respostaErro, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespostaErro> handleGlobalException(Exception ex, WebRequest request) {
        RespostaErro respostaErro = getRespostaErro(ex, request);
        return new ResponseEntity<>(respostaErro, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<RespostaErro> handleLoginException(LoginException ex, WebRequest request) {
        RespostaErro respostaErro = getRespostaErro(ex, request);
        return new ResponseEntity<>(respostaErro, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<RespostaErro> handleNegocioException(NegocioException ex, WebRequest request) {
        RespostaErro respostaErro = getRespostaErro(ex, request);
        return new ResponseEntity<>(respostaErro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(S3Exception.class)
    public ResponseEntity<RespostaErro> handleS3Exception(S3Exception ex, WebRequest request) {
        RespostaErro respostaErro = getRespostaErro(ex, request);
        return new ResponseEntity<>(respostaErro, HttpStatus.BAD_REQUEST);
    }

    private static RespostaErro getRespostaErro(Exception ex, WebRequest request) {

        RespostaErro respostaErro;

        if (ex instanceof HandlerException) {
            ApplicationException appEx = (ApplicationException) ex;
            respostaErro = new RespostaErro(
                    LocalDateTime.now(),
                    appEx.getErro().getMensagem(),
                    request.getDescription(false),
                    appEx.getErro().getStatusCode()
            );
        } else {
            respostaErro = new RespostaErro(
                    LocalDateTime.now(),
                    "Erro de servidor",
                    request.getDescription(false),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return respostaErro;
    }

}

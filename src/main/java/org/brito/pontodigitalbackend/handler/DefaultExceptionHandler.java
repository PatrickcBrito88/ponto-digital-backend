package org.brito.pontodigitalbackend.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.brito.pontodigitalbackend.exception.ApplicationException;
import org.brito.pontodigitalbackend.exception.models.EntityErrorResponse;
import org.brito.pontodigitalbackend.utils.ExceptionHandlerUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;

@RestControllerAdvice
public class DefaultExceptionHandler {

    private final ExceptionHandlerUtils exceptionHandlerUtils;

    public DefaultExceptionHandler(ExceptionHandlerUtils exceptionHandlerUtils) {
        this.exceptionHandlerUtils = exceptionHandlerUtils;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<EntityErrorResponse> handleDefaultException(
        HttpServletRequest req,
        HttpServletResponse res,
        @Nullable ApplicationException ex
    ) {
        return exceptionHandlerUtils.buildResponseEntityError(req, res, ex);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<EntityErrorResponse> handleValidationException(
            HttpServletRequest req,
            HttpServletResponse res,
            MethodArgumentNotValidException ex) {
        return exceptionHandlerUtils.buildResponseEntityValidationError(req, res, ex);
    }

}

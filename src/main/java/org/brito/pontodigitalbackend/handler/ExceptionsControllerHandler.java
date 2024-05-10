package org.brito.pontodigitalbackend.handler;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.brito.pontodigitalbackend.exception.models.EntityErrorResponse;
import org.brito.pontodigitalbackend.utils.ExceptionHandlerUtils;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/error")
@AllArgsConstructor
public class ExceptionsControllerHandler implements ErrorController {

    private final ExceptionHandlerUtils exceptionHandlerUtils;

    @RequestMapping
    public ResponseEntity<EntityErrorResponse> error(
        HttpServletRequest req,
        HttpServletResponse res
    ) {
        return exceptionHandlerUtils.buildResponseEntityError(req, res, null);
    }
}

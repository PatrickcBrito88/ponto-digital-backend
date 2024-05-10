package org.brito.pontodigitalbackend.utils;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.brito.pontodigitalbackend.domain.DefaultError;
import org.brito.pontodigitalbackend.enums.CodigoHttp;
import org.brito.pontodigitalbackend.exception.ApplicationException;
import org.brito.pontodigitalbackend.exception.models.EntityErrorResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ExceptionHandlerUtils {

    @Value("${spring.application.name}")
    private String nmServico;

    private void setMetaDado(Map<String, Object> map, String idTransacao) {
        map.put("nmServico", nmServico);
        map.put("idTransacao", idTransacao);
    }

    public ResponseEntity<EntityErrorResponse> buildResponseEntityError(
            HttpServletRequest req,
            HttpServletResponse res,
            @Nullable ApplicationException ex
    ) {

        Optional<DefaultError> erroOptional = Optional.ofNullable(ex)
                .map(ApplicationException::getErro);

        Map<String, Object> metaDado = erroOptional
                .map(DefaultError::getMetaDado)
                .orElse(new HashMap<String, Object>());

        HttpStatus status = erroOptional
                .map(DefaultError::getStatusCode)
                .orElse(HttpStatus.valueOf(res.getStatus()));

        Map<String, Object> detalheMap = new HashMap<>();

        CodigoHttp codigoHttp = CodigoHttp.valueOf(status.value());

        detalheMap.put("mensagem", erroOptional.map(DefaultError::getMensagem).orElse(codigoHttp.getDetalhe()));

        return buildDefaultEntityError(req, metaDado, status, detalheMap, codigoHttp);
    }

    public ResponseEntity<EntityErrorResponse> buildResponseEntityValidationError(
            HttpServletRequest req,
            HttpServletResponse res,
            MethodArgumentNotValidException ex) {

        Map<String, Object> metaDado = new HashMap<>();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CodigoHttp codigoHttp = CodigoHttp.valueOf(status.value());
        List<Map<String, String>> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> {
                    Map<String, String> fieldErrorMap = new HashMap<>();
                    fieldErrorMap.put("campo", error.getField());
                    fieldErrorMap.put("mensagem", error.getDefaultMessage());
                    return fieldErrorMap;
                })
                .collect(Collectors.toList());

        String detalhe = "Erro de validação. Verifique os campos:";

        Map<String, Object> detalheMap = new HashMap<>();
        detalheMap.put("erros", fieldErrors);
        detalheMap.put("mensagem", detalhe);

        return buildDefaultEntityError(req, metaDado, status, detalheMap, codigoHttp);
    }

    private ResponseEntity<EntityErrorResponse> buildDefaultEntityError(HttpServletRequest req,
                                                                        Map<String, Object> metaDado,
                                                                        HttpStatus status,
                                                                        Map<String, Object> detalheMap,
                                                                        CodigoHttp codigoHttp) {
        String idTransacao = req.getHeader("nmIdTransacao");
        setMetaDado(metaDado, idTransacao);

        EntityErrorResponse body = EntityErrorResponse
                .builder()
                .id(idTransacao)
                .status(String.valueOf(status.value()))
                .codigo(codigoHttp.getCodigo())
                .titulo(status.getReasonPhrase())
                .detalhe(detalheMap)
                .vinculos(new EntityErrorResponse.Vinculos(req.getRequestURL().toString()))
                .metaDado(metaDado)
                .build();

        return new ResponseEntity<>(body, status);

    }

}

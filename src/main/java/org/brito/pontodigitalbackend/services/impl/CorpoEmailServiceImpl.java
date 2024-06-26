package org.brito.pontodigitalbackend.services.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.brito.pontodigitalbackend.exception.NegocioException;
import org.brito.pontodigitalbackend.services.CorpoEmailService;
import org.brito.pontodigitalbackend.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CorpoEmailServiceImpl implements CorpoEmailService {

    @Autowired
    private Configuration freemarkerConfig;

    @Override
    public String geraCorpoEmailSenhaTemporaria(String nome, String senhaTemporaria) {
        Map<String, Object> model = new HashMap<>();
        model.put("nome", nome);
        model.put("senhaTemporaria", senhaTemporaria);
        String corpoEmail = "";

        try {
            Template template = freemarkerConfig.getTemplate("senhaTemporariaTemplate.ftlh");
            corpoEmail = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (TemplateException | IOException e) {
            throw new NegocioException(
                    MessageUtils.buscaMensagemValidacao(
                            "free.maker.erro.geracao.template",
                            e.getMessage()));
        }

        return corpoEmail;

    }

}

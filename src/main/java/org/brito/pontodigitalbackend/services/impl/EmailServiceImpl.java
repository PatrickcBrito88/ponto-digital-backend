package org.brito.pontodigitalbackend.services.impl;

import freemarker.template.TemplateException;
import org.brito.pontodigitalbackend.domain.Funcionario;
import org.brito.pontodigitalbackend.exception.EmailException;
import org.brito.pontodigitalbackend.services.CorpoEmailService;
import org.brito.pontodigitalbackend.services.EmailService;
import org.brito.pontodigitalbackend.services.S3Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.brito.pontodigitalbackend.constantes.EmailConstantes.*;
import static org.brito.pontodigitalbackend.utils.JsonUtils.geraJson;
import static org.brito.pontodigitalbackend.utils.S3Utils.geraKeyS3;

@Service
public class EmailServiceImpl implements EmailService {


    final CorpoEmailService corpoEmailService;

    final S3Service s3Service;

    @Value("${mail.from}")
    String emailFrom;

    @Value("${s3.bucket.email}")
    String nomeBucket;

    public EmailServiceImpl(CorpoEmailService corpoEmailService, S3Service s3Service) {
        this.corpoEmailService = corpoEmailService;
        this.s3Service = s3Service;
    }


    @Override
    public void gerarEmailSenhaProvisoria(Funcionario funcionario, String senhaDescriptografada) throws EmailException {
        String corpoEmail;
        try {
            corpoEmail = corpoEmailService.geraCorpoEmailSenhaTemporaria(funcionario.getNome(), senhaDescriptografada);
        } catch (TemplateException | IOException e) {
            throw new RuntimeException("Erro ao gerar corpo do e-mail: " + e.getMessage());
        }

        if (corpoEmail == null || corpoEmail.trim().isEmpty()) {
            throw new RuntimeException("Corpo do e-mail está vazio. E-mail não será enviado.");
        }

        Map<String, String> jsonMap = geraObjetoEmail(funcionario, corpoEmail);

        String caminhoArquivo = geraKeyS3(
                DIR_SENHAS_TEMPORARIAS,
                NOME_ARQUIVO_SENHAS_TEMPORARIAS,
                String.valueOf(funcionario.getId()));
        try {
            String emailJson = geraJson(jsonMap);
            s3Service.uploadArquivo(nomeBucket, caminhoArquivo, emailJson.getBytes(), "application/json");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar e-mail no repositório: " + e.getMessage());
        }
    }


    private Map<String, String> geraObjetoEmail(Funcionario funcionario, String corpoEmail) {
        Map<String, String> jsonMap = new HashMap<>();
        jsonMap.put("de", emailFrom);
        jsonMap.put("para", funcionario.getEmail());
        jsonMap.put("assunto", TITULO_SENHA_TEMPORARIA);
        jsonMap.put("mensagem", corpoEmail);
        return jsonMap;
    }

}


package org.brito.pontodigitalbackend.services;

import freemarker.template.TemplateException;

import java.io.IOException;

public interface CorpoEmailService {

    String geraCorpoEmailSenhaTemporaria(String nome, String senhaTemporaria) throws TemplateException, IOException;

}

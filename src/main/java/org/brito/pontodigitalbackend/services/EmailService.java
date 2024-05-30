package org.brito.pontodigitalbackend.services;

import freemarker.template.TemplateException;
import org.brito.pontodigitalbackend.domain.Funcionario;

import java.io.IOException;

public interface EmailService {

    void gerarEmailSenhaProvisoria(Funcionario funcionario, String senhaDescriptografada) throws TemplateException, IOException;
}

package org.brito.pontodigitalbackend.services;

import freemarker.template.TemplateException;
import org.brito.pontodigitalbackend.domain.Funcionario;
import org.brito.pontodigitalbackend.dtos.CadastroUsuarioDTO;

import java.io.IOException;

public interface FuncionarioService {

    Funcionario cadastrarFuncionario(CadastroUsuarioDTO cadastroUsuarioDTO) throws TemplateException, IOException;

    Funcionario buscarPeloId(Long id);
}

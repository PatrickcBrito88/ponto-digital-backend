package org.brito.pontodigitalbackend.services;

import org.brito.pontodigitalbackend.dtos.CadastroUsuarioDTO;

public interface UsuarioService {

    String cadastrarUsuario(CadastroUsuarioDTO cadastroUsuarioDTO);
}

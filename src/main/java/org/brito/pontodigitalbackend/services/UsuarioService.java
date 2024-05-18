package org.brito.pontodigitalbackend.services;

import org.brito.pontodigitalbackend.domain.user.Usuario;
import org.brito.pontodigitalbackend.dtos.AdminDTO;
import org.brito.pontodigitalbackend.dtos.CadastroUsuarioDTO;

public interface UsuarioService {

    String cadastrarUsuario(CadastroUsuarioDTO cadastroUsuarioDTO);

    Usuario buscarPeloId(Long id);

    String alterarSenhaAdm(AdminDTO adminDTO);
}

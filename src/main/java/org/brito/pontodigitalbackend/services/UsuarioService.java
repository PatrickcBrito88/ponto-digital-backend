package org.brito.pontodigitalbackend.services;

import org.brito.pontodigitalbackend.domain.user.Usuario;
import org.brito.pontodigitalbackend.dtos.AdminDTO;

public interface UsuarioService {

    Usuario cadastrarUsuario(String login, String senhaTemporaria);

    String alterarSenhaAdm(AdminDTO adminDTO);

    Usuario buscaNomeUsuario(Long idUsuario);

}

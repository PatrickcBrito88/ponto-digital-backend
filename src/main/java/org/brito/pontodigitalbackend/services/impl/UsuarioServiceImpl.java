package org.brito.pontodigitalbackend.services.impl;

import org.brito.pontodigitalbackend.domain.user.UserRole;
import org.brito.pontodigitalbackend.domain.user.Usuario;
import org.brito.pontodigitalbackend.dtos.CadastroUsuarioDTO;
import org.brito.pontodigitalbackend.exception.LoginException;
import org.brito.pontodigitalbackend.repositories.UsuarioRepository;
import org.brito.pontodigitalbackend.services.UsuarioService;
import org.brito.pontodigitalbackend.utils.MessageUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static org.brito.pontodigitalbackend.utils.GeradorSenha.generatePassword;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ModelMapper mapper;


    @Override
    public String cadastrarUsuario(CadastroUsuarioDTO cadastroUsuarioDTO) {
        Usuario usuario = mapper.map(cadastroUsuarioDTO, Usuario.class);
        usuario.setPassword(buscaSenhaCriptografada(usuario.getLogin(), generatePassword()));
        usuario.setRole(UserRole.USER);

        usuarioRepository.save(usuario);

        return MessageUtils.buscaMensagemValidacao("usuario.cadastrado.sucesso", usuario.getLogin());
    }

    private String buscaSenhaCriptografada(String login, String senhaGerada){
/**/        if (this.usuarioRepository.findByLogin(login) != null) {
            throw new LoginException(MessageUtils.buscaMensagemValidacao("login.usuario.ja.cadastrado"));
        }

        return new BCryptPasswordEncoder().encode(senhaGerada);

    }
}

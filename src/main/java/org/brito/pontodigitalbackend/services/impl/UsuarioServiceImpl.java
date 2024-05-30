package org.brito.pontodigitalbackend.services.impl;

import jakarta.transaction.Transactional;
import org.brito.pontodigitalbackend.domain.user.UserRole;
import org.brito.pontodigitalbackend.domain.user.Usuario;
import org.brito.pontodigitalbackend.dtos.AdminDTO;
import org.brito.pontodigitalbackend.exception.NegocioException;
import org.brito.pontodigitalbackend.repositories.UsuarioRepository;
import org.brito.pontodigitalbackend.services.CorpoEmailService;
import org.brito.pontodigitalbackend.services.UsuarioService;
import org.brito.pontodigitalbackend.utils.MessageUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    final
    UsuarioRepository usuarioRepository;

    final
    ModelMapper mapper;

    final
    CorpoEmailService corpoEmailService;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, ModelMapper mapper, CorpoEmailService corpoEmailService) {
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
        this.corpoEmailService = corpoEmailService;
    }


    @Override
    @Transactional
    public Usuario cadastrarUsuario(String login, String senhaCriptografada) {
        verificaUsuarioExistente(login);
        Usuario usuario = new Usuario(
                login,
                senhaCriptografada,
                UserRole.USER
        );
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public String alterarSenhaAdm(AdminDTO adminDTO) {
        Usuario usuario = new Usuario();
        usuario.setRole(UserRole.ADMIN);
        usuario.setLogin("Administrador");
        usuario.setPassword(new BCryptPasswordEncoder().encode(adminDTO.getPassword()));

        usuarioRepository.save(usuario);

        return MessageUtils.buscaMensagemValidacao("usuario.adm.senha.atualizada");
    }

    private void verificaUsuarioExistente(String login) {
        if (this.usuarioRepository.findByLogin(login) != null) {
            throw new NegocioException(MessageUtils.buscaMensagemValidacao("login.usuario.ja.cadastrado", login));
        }

    }

}

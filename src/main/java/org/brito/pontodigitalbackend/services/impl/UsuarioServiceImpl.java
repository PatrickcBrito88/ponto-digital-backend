package org.brito.pontodigitalbackend.services.impl;

import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import org.brito.pontodigitalbackend.domain.user.UserRole;
import org.brito.pontodigitalbackend.domain.user.Usuario;
import org.brito.pontodigitalbackend.dtos.AdminDTO;
import org.brito.pontodigitalbackend.dtos.CadastroUsuarioDTO;
import org.brito.pontodigitalbackend.exception.LoginException;
import org.brito.pontodigitalbackend.exception.NaoEncontradoException;
import org.brito.pontodigitalbackend.exception.ServicoException;
import org.brito.pontodigitalbackend.repositories.UsuarioRepository;
import org.brito.pontodigitalbackend.services.CorpoEmailService;
import org.brito.pontodigitalbackend.services.EmailService;
import org.brito.pontodigitalbackend.services.UsuarioService;
import org.brito.pontodigitalbackend.utils.MessageUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static org.brito.pontodigitalbackend.constantes.EmailConstantes.TITULO_SENHA_TEMPORARIA;
import static org.brito.pontodigitalbackend.utils.GeradorSenha.generatePassword;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ModelMapper mapper;

    @Autowired
    EmailService emailService;

    @Autowired
    CorpoEmailService corpoEmailService;


    @Override
    public String cadastrarUsuario(CadastroUsuarioDTO cadastroUsuarioDTO) {
        Usuario usuario = mapper.map(cadastroUsuarioDTO, Usuario.class);
        verificaUsuarioExistente(usuario.getLogin());
        String senhaTemporaria = generatePassword();
        usuario.setPassword(new BCryptPasswordEncoder().encode(senhaTemporaria));
        usuario.setRole(UserRole.USER);
        usuario.setPrimeiroAcesso(true);

        usuarioRepository.save(usuario);

        try {
            String corpoEmail = corpoEmailService.geraCorpoEmailSenhaTemporaria(usuario.getNome(), senhaTemporaria);
            emailService.enviarEmail(usuario.getEmail(), TITULO_SENHA_TEMPORARIA, corpoEmail);
        } catch (ServicoException | TemplateException | IOException e) {
            throw new ServicoException(e.getMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return MessageUtils.buscaMensagemValidacao("usuario.cadastrado.sucesso", usuario.getLogin());
    }

    @Override
    public Usuario buscarPeloId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new NaoEncontradoException(
                                MessageUtils.buscaMensagemValidacao("usuario.nao.encontrado", id)));
    }

    @Override
    public String alterarSenhaAdm(AdminDTO adminDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome("Administrador");
        usuario.setRole(UserRole.ADMIN);
        usuario.setLogin("Administrador");
        usuario.setPassword(new BCryptPasswordEncoder().encode(adminDTO.getPassword()));
        usuario.setPrimeiroAcesso(Boolean.FALSE);

        usuarioRepository.save(usuario);

        return MessageUtils.buscaMensagemValidacao("usuario.adm.senha.atualizada");
    }

    private void verificaUsuarioExistente(String login) {
        if (this.usuarioRepository.findByLogin(login) != null) {
            throw new LoginException(MessageUtils.buscaMensagemValidacao("login.usuario.ja.cadastrado", login));
        }

    }

}

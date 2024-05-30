package org.brito.pontodigitalbackend.services.impl;

import freemarker.template.TemplateException;
import jakarta.transaction.Transactional;
import org.brito.pontodigitalbackend.domain.Funcionario;
import org.brito.pontodigitalbackend.domain.user.Usuario;
import org.brito.pontodigitalbackend.dtos.CadastroUsuarioDTO;
import org.brito.pontodigitalbackend.exception.NaoEncontradoException;
import org.brito.pontodigitalbackend.repositories.FuncionarioRepository;
import org.brito.pontodigitalbackend.services.CorpoEmailService;
import org.brito.pontodigitalbackend.services.EmailService;
import org.brito.pontodigitalbackend.services.FuncionarioService;
import org.brito.pontodigitalbackend.services.UsuarioService;
import org.brito.pontodigitalbackend.utils.MessageUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static org.brito.pontodigitalbackend.utils.GeradorSenha.generatePassword;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    final
    FuncionarioRepository funcionarioRepository;

    final EmailService emailService;

    final
    CorpoEmailService corpoEmailService;

    final
    UsuarioService usuarioService;

    final
    ModelMapper mapper;

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository, EmailService emailService, CorpoEmailService corpoEmailService, UsuarioService usuarioService, ModelMapper mapper) {
        this.funcionarioRepository = funcionarioRepository;
        this.emailService = emailService;
        this.corpoEmailService = corpoEmailService;
        this.usuarioService = usuarioService;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public Funcionario cadastrarFuncionario(CadastroUsuarioDTO cadastroUsuarioDTO) throws TemplateException, IOException {
        String senhaDescriptografada = generatePassword();
        String senhaCriptografada = new BCryptPasswordEncoder().encode(senhaDescriptografada);
        Usuario usuario = usuarioService.cadastrarUsuario(cadastroUsuarioDTO.getLogin(), senhaCriptografada);

        Funcionario funcionario = mapper.map(cadastroUsuarioDTO, Funcionario.class);
        funcionario.setUser(usuario);
        funcionario = funcionarioRepository.save(funcionario);

        emailService.gerarEmailSenhaProvisoria(
                funcionario,
                senhaDescriptografada);

        return funcionario;
    }

    @Override
    public Funcionario buscarPeloId(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() ->
                    new NaoEncontradoException(MessageUtils.buscaMensagemValidacao("funcionario.nao.encontrado", id)));

    }
}

package org.brito.pontodigitalbackend.services.impl;

import org.brito.pontodigitalbackend.domain.user.AuthenticationDTO;
import org.brito.pontodigitalbackend.domain.user.LoginResponseDTO;
import org.brito.pontodigitalbackend.domain.user.RegisterDTO;
import org.brito.pontodigitalbackend.domain.user.Usuario;
import org.brito.pontodigitalbackend.exception.LoginException;
import org.brito.pontodigitalbackend.repositories.UsuarioRepository;
import org.brito.pontodigitalbackend.security.TokenService;
import org.brito.pontodigitalbackend.services.LoginService;
import org.brito.pontodigitalbackend.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;


    @Override
    public LoginResponseDTO login(AuthenticationDTO data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((Usuario) auth.getPrincipal());

            return new LoginResponseDTO(token);
        } catch (Exception e) {
            throw new LoginException(e.getMessage());
        }
    }

    @Override
    public String register(RegisterDTO data) {
        if (this.repository.findByLogin(data.login()) != null) {
            throw new LoginException(MessageUtils.buscaMensagemValidacao("login.usuario.ja.cadastrado"));
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Usuario newUserAuthentication = new Usuario(data.login(), encryptedPassword, data.role());

        this.repository.save(newUserAuthentication);

        return MessageUtils.buscaMensagemValidacao("login.usuario.cadastrado.sucesso");
    }
}

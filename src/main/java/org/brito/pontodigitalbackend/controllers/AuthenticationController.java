package org.brito.pontodigitalbackend.controllers;


import jakarta.validation.Valid;
import org.brito.pontodigitalbackend.controllers.models.DefaultController;
import org.brito.pontodigitalbackend.controllers.models.DefaultResponse;
import org.brito.pontodigitalbackend.domain.user.AuthenticationDTO;
import org.brito.pontodigitalbackend.domain.user.LoginResponseDTO;
import org.brito.pontodigitalbackend.dtos.AdminDTO;
import org.brito.pontodigitalbackend.services.LoginService;
import org.brito.pontodigitalbackend.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController implements DefaultController {

    private final LoginService loginService;

    private final UsuarioService usuarioService;

    public AuthenticationController(LoginService loginService, UsuarioService usuarioService) {
        this.loginService = loginService;
        this.usuarioService = usuarioService;
    }


    @PostMapping("/login")
    public ResponseEntity<DefaultResponse<LoginResponseDTO>> login(@RequestBody @Valid AuthenticationDTO data){
        return retornarSucesso(loginService.login(data));
    }

    @PutMapping("/altera-senha-adm")
    public ResponseEntity<DefaultResponse<String>> alteraSenhaAdm(@RequestBody @Valid AdminDTO adminDTO){
        return retornarSucesso(usuarioService.alterarSenhaAdm(adminDTO));
    }

}

package org.brito.pontodigitalbackend.controllers;


import jakarta.validation.Valid;
import org.brito.pontodigitalbackend.controllers.models.DefaultController;
import org.brito.pontodigitalbackend.controllers.models.DefaultResponse;
import org.brito.pontodigitalbackend.domain.user.AuthenticationDTO;
import org.brito.pontodigitalbackend.domain.user.LoginResponseDTO;
import org.brito.pontodigitalbackend.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController implements DefaultController {

    @Autowired
   private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<DefaultResponse<LoginResponseDTO>> login(@RequestBody @Valid AuthenticationDTO data){
        return retornarSucesso(loginService.login(data));
    }

}

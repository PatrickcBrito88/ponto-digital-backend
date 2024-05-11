package org.brito.pontodigitalbackend.controllers;

import jakarta.validation.Valid;
import org.brito.pontodigitalbackend.controllers.models.DefaultController;
import org.brito.pontodigitalbackend.controllers.models.DefaultResponse;
import org.brito.pontodigitalbackend.dtos.CadastroUsuarioDTO;
import org.brito.pontodigitalbackend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuario")
public class UsuarioController implements DefaultController {


    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<DefaultResponse<String>> login(@RequestBody @Valid CadastroUsuarioDTO data){
        return retornarSucesso(usuarioService.cadastrarUsuario(data));
    }


}

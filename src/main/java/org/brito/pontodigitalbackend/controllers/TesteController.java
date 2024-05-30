package org.brito.pontodigitalbackend.controllers;

import org.brito.pontodigitalbackend.controllers.models.DefaultController;
import org.brito.pontodigitalbackend.controllers.models.DefaultResponse;
import org.brito.pontodigitalbackend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teste")
public class TesteController implements DefaultController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public ResponseEntity<DefaultResponse<String>> teste(){
        //usuarioService.buscarPeloId(500L);


        return retornarSucesso("Teste");

    }
}

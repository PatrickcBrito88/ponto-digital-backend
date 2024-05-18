package org.brito.pontodigitalbackend.controllers;

import org.brito.pontodigitalbackend.controllers.models.DefaultController;
import org.brito.pontodigitalbackend.controllers.models.DefaultResponse;
import org.brito.pontodigitalbackend.dtos.PontoUsuarioDTO;
import org.brito.pontodigitalbackend.dtos.PontoUsuarioRegistroDTO;
import org.brito.pontodigitalbackend.services.PontoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ponto-usuario")
public class PontoUsuarioController implements DefaultController {

    @Autowired
    PontoUsuarioService pontoUsuarioService;


    @PostMapping("/salvar")
    public ResponseEntity<DefaultResponse<String>> salvarPonto(@RequestBody PontoUsuarioRegistroDTO pontoUsuario){
        return retornarSucesso(pontoUsuarioService.salvarPontoUsuario(pontoUsuario));
    }

    @GetMapping("/buscar/{idUsuario}")
    public ResponseEntity<DefaultResponse<PontoUsuarioDTO>> buscarPonto(@PathVariable int idUsuario){
        return retornarSucesso(pontoUsuarioService.buscarPontoUsuario((long) idUsuario));
    }
}

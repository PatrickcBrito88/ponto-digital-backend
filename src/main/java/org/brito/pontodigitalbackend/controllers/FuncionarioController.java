package org.brito.pontodigitalbackend.controllers;

import freemarker.template.TemplateException;
import jakarta.validation.Valid;
import org.brito.pontodigitalbackend.controllers.models.DefaultController;
import org.brito.pontodigitalbackend.controllers.models.DefaultResponse;
import org.brito.pontodigitalbackend.dtos.CadastroUsuarioDTO;
import org.brito.pontodigitalbackend.services.FuncionarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("funcionario")
public class FuncionarioController implements DefaultController {


    final
    FuncionarioService funcionarioService;



    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping("/registro")
    public ResponseEntity<DefaultResponse<String>> login(@RequestBody @Valid CadastroUsuarioDTO data) throws TemplateException, IOException {
        funcionarioService.cadastrarFuncionario(data);
        return retornarSucesso("Cadastrado com sucesso");
    }




}

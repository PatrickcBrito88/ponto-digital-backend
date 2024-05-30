package org.brito.pontodigitalbackend.controllers;

import org.brito.pontodigitalbackend.controllers.models.DefaultController;
import org.brito.pontodigitalbackend.controllers.models.DefaultResponse;
import org.brito.pontodigitalbackend.dtos.JustificativaUsuarioDTO;
import org.brito.pontodigitalbackend.dtos.PontoUsuarioDTO;
import org.brito.pontodigitalbackend.dtos.PontoUsuarioRegistroDTO;
import org.brito.pontodigitalbackend.services.PontoUsuarioService;
import org.brito.pontodigitalbackend.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("ponto-usuario")
public class PontoUsuarioController implements DefaultController {

    @Autowired
    PontoUsuarioService pontoUsuarioService;

    @Autowired
    S3Service s3Service;


    @PostMapping("/salvar")
    public ResponseEntity<DefaultResponse<String>> salvarPonto(@RequestBody PontoUsuarioRegistroDTO pontoUsuario) {
        return retornarSucesso(pontoUsuarioService.salvarPontoUsuario(pontoUsuario));
    }

    @PostMapping("/justificativa")
    public ResponseEntity<DefaultResponse<String>> salvarJustificativa(@RequestBody JustificativaUsuarioDTO justificativaUsuarioDTO) {
        return retornarSucesso(pontoUsuarioService.salvarJutificativaUsuario(justificativaUsuarioDTO));
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<DefaultResponse<PontoUsuarioDTO>> buscarPonto(@PathVariable int idUsuario) {
        return retornarSucesso(pontoUsuarioService.buscarPontoUsuarioPorId((long) idUsuario));
    }

    @GetMapping("/todos")
    public ResponseEntity<DefaultResponse<Page<PontoUsuarioDTO>>> buscaTodosPontos(@RequestParam(required = false) Integer paginaAtual,
                                                                                   @RequestParam(required = false) Integer tamanhoPagina) {
        return retornarSucesso(pontoUsuarioService.buscaTodosPontos(paginaAtual, tamanhoPagina));
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        //s3Service.uploadArquivo(file);
        return ResponseEntity.ok("OK");
    }
}

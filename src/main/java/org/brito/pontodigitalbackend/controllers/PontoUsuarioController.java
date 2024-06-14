package org.brito.pontodigitalbackend.controllers;

import org.brito.pontodigitalbackend.controllers.models.DefaultController;
import org.brito.pontodigitalbackend.controllers.models.DefaultResponse;
import org.brito.pontodigitalbackend.domain.PontoUsuario;
import org.brito.pontodigitalbackend.dtos.*;
import org.brito.pontodigitalbackend.enums.EStatusPonto;
import org.brito.pontodigitalbackend.services.PontoUsuarioService;
import org.brito.pontodigitalbackend.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

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
    public ResponseEntity<DefaultResponse<JustificativaDTO>> salvarJustificativa(@RequestBody JustificativaUsuarioDTO justificativaUsuarioDTO) {
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

    @PostMapping("/anexo/upload")
    public ResponseEntity<DefaultResponse<String>> uploadArquivo(@RequestParam("file") MultipartFile file,
                                                                 @RequestParam LocalDate data,
                                                                 @RequestParam String idFuncionario) throws IOException {
        return retornarSucesso(pontoUsuarioService.uploadAnexo(file, idFuncionario, data));
    }

    @GetMapping("/anexo/download")
    public ResponseEntity<DefaultResponse<String>> downloadArquivo(@RequestParam LocalDate data,
                                                                   @RequestParam String idFuncionario,
                                                                   @RequestParam String nomeAnexo) throws IOException {
        return retornarSucesso(pontoUsuarioService.downloadAnexo(nomeAnexo, idFuncionario, data));
    }

    @DeleteMapping("/anexo")
    public ResponseEntity<DefaultResponse<String>> deleteArquivo(@RequestParam LocalDate data,
                                                                 @RequestParam String idFuncionario,
                                                                 @RequestParam String nomeAnexo) {
        return retornarSucesso(pontoUsuarioService.apagarAnexo(nomeAnexo, idFuncionario, data));
    }

    @PutMapping("/aprovar")
    public ResponseEntity<DefaultResponse<String>> aprovarPonto(@RequestParam LocalDate data,
                                                                @RequestParam String idFuncionario,
                                                                @RequestParam EStatusPonto situacao) {
        return retornarSucesso(pontoUsuarioService.aprovarPonto(data, idFuncionario, situacao));
    }

    @GetMapping("/validar")
    public ResponseEntity<DefaultResponse<PontoUsuario>> validarPonto(@RequestParam LocalDate data,
                                                                      @RequestParam String idFuncionario) {
        return retornarSucesso(pontoUsuarioService.validarPonto(data, idFuncionario));
    }

    @PutMapping("/ajuste-horario")
    public ResponseEntity<DefaultResponse<String>> ajusteHorario(@RequestBody HorariosAlteracaoDTO horariosAlteracaoDTO) {
        return retornarSucesso(pontoUsuarioService.ajustePontoEmpregador(horariosAlteracaoDTO));
    }

    @PutMapping("/funcionario-ciente")
    public ResponseEntity<DefaultResponse<String>> confirmaAlteracaoPontoFuncionario(@RequestParam LocalDate data,
                                                                                     @RequestParam String idFuncionario) {
        return retornarSucesso(pontoUsuarioService.confirmaAlteracaoPontoFuncionario(data, idFuncionario));
    }

    @PutMapping("/solicitar-ajuste")
    public ResponseEntity<DefaultResponse<String>> solicitarAjustePontoFuncionario(@RequestBody JustificativaUsuarioDTO justificativaUsuarioDTO) {
        return retornarSucesso(pontoUsuarioService.solicitarAjustePonto(justificativaUsuarioDTO));
    }


}

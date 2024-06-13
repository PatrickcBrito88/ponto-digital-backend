package org.brito.pontodigitalbackend.services.impl;

import org.brito.pontodigitalbackend.domain.HorarioAlterado;
import org.brito.pontodigitalbackend.domain.Justificativa;
import org.brito.pontodigitalbackend.domain.PontoUsuario;
import org.brito.pontodigitalbackend.domain.pk.PontoUsuarioPK;
import org.brito.pontodigitalbackend.domain.user.Usuario;
import org.brito.pontodigitalbackend.dtos.*;
import org.brito.pontodigitalbackend.enums.EStatusPonto;
import org.brito.pontodigitalbackend.repositories.PontoUsuarioRepository;
import org.brito.pontodigitalbackend.repositories.UsuarioRepository;
import org.brito.pontodigitalbackend.services.FuncionarioService;
import org.brito.pontodigitalbackend.services.PontoUsuarioService;
import org.brito.pontodigitalbackend.services.S3Service;
import org.brito.pontodigitalbackend.services.UsuarioService;
import org.brito.pontodigitalbackend.utils.Paginador;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.brito.pontodigitalbackend.utils.ComparadorHorarioUtils.gerarHorariosAlterados;
import static org.brito.pontodigitalbackend.utils.DataHoraUtils.buscaDataHoraAgora;
import static org.brito.pontodigitalbackend.utils.FileUtils.getFileExtension;
import static org.brito.pontodigitalbackend.utils.FileUtils.getFileName;
import static org.brito.pontodigitalbackend.utils.S3Utils.geraKeyAnexo;

@Service
public class PontoUsuarioServiceImpl implements PontoUsuarioService {

    public static final String ADMIN = "ADMIN";
    final
    PontoUsuarioRepository pontoUsuarioRepository;

    final
    UsuarioRepository usuarioRepository;

    final
    UsuarioService usuarioService;

    final
    FuncionarioService funcionarioService;

    final S3Service s3Service;

    final ModelMapper mapper;

    @Value("${s3.bucket.anexo}")
    String nomeBucket;

    public PontoUsuarioServiceImpl(PontoUsuarioRepository pontoUsuarioRepository, UsuarioRepository usuarioRepository, UsuarioService usuarioService, FuncionarioService funcionarioService, S3Service s3Service, ModelMapper mapper) {
        this.pontoUsuarioRepository = pontoUsuarioRepository;
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
        this.funcionarioService = funcionarioService;
        this.s3Service = s3Service;
        this.mapper = mapper;
    }


    @Override
    public String salvarPontoUsuario(PontoUsuarioRegistroDTO pontoUsuarioRegistroDTO) {
        PontoUsuario pontoUsuario = geraPontoUsuario(pontoUsuarioRegistroDTO);
        pontoUsuarioRepository.save(pontoUsuario);
        return "OK";
    }

    @Override
    public PontoUsuarioDTO buscarPontoUsuarioPorId(Long idUsuario) {
        List<PontoUsuario> lista = pontoUsuarioRepository.buscarPorUsuarioId(idUsuario);
        return gerarPontoUsuario(idUsuario, lista);
    }

    private static PontoUsuarioDTO gerarPontoUsuario(Long idUsuario, List<PontoUsuario> lista) {
        List<PontoDTO> listaPonto =
                lista.stream()
                        .map(item -> new PontoDTO(
                                item.getId().getData(),
                                item.getEntrada(),
                                item.getInicioAlmoco(),
                                item.getFimAlmoco(),
                                item.getSaida(),
                                item.getJustificativas(),
                                item.getAnexos(),
                                item.getSituacao(),
                                item.getHorariosAlterados()))
                        .collect(Collectors.toList());

        return new PontoUsuarioDTO(idUsuario, listaPonto);
    }

    @Override
    public Page<PontoUsuarioDTO> buscaTodosPontos(Integer paginaAtual, Integer tamanhoPagina) {
        List<PontoUsuario> lista = pontoUsuarioRepository.findAll();

        List<PontoUsuarioDTO> listaPontoUsuarioDTO = new ArrayList<>(lista.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getId().getUsuarioId(),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                pl -> {
                                    PontoUsuarioDTO dto = new PontoUsuarioDTO();
                                    dto.setIdUsuario(pl.get(0).getId().getUsuarioId());
                                    List<PontoDTO> pontoDTOs = pl.stream().map(p -> {
                                        return new PontoDTO(
                                                p.getId().getData(),
                                                p.getEntrada(),
                                                p.getInicioAlmoco(),
                                                p.getFimAlmoco(),
                                                p.getSaida(),
                                                p.getJustificativas(),
                                                p.getAnexos(),
                                                p.getSituacao(),
                                                p.getHorariosAlterados());
                                    }).collect(Collectors.toList());
                                    dto.setPonto(pontoDTOs);
                                    return dto;
                                }
                        )
                ))
                .values());

        List<PontoUsuarioDTO> listaPontoUsuario = listaPontoUsuarioDTO.stream()
                .peek(u -> {
                    String nomeCompleto = funcionarioService.buscarPeloId(u.getIdUsuario()).getNome();
                    u.setNomeCompleto(nomeCompleto);
                })
                .collect(Collectors.toList());

        return Paginador.gerarPaginacao(listaPontoUsuario, paginaAtual, tamanhoPagina);
    }

    @Override
    public JustificativaDTO salvarJutificativaUsuario(JustificativaUsuarioDTO justificativaUsuarioDTO) {
        Long idUsuario = Long.valueOf(justificativaUsuarioDTO.getIdUsuario());
        LocalDate data = justificativaUsuarioDTO.getData();
        PontoUsuario pontoUsuario = pontoUsuarioRepository.buscarPorUsuarioEData(idUsuario, data);
        Usuario usuario = usuarioService.buscaNomeUsuario(idUsuario);
        Justificativa justificativa = new Justificativa(
                justificativaUsuarioDTO.getJustificativa(),
                buscaDataHoraAgora(),
                usuario.getUsername()
        );

        pontoUsuario.getJustificativas().add(justificativa);

        pontoUsuarioRepository.save(pontoUsuario);

        return mapper.map(justificativa, JustificativaDTO.class);

    }

    @Override
    public String uploadAnexo(MultipartFile file, String idFuncionario, LocalDate data) throws IOException {
        String nomeArquivo = getFileName(file);
        String extensaoArquivo = getFileExtension(file);
        String pathAquivo = geraKeyAnexo(idFuncionario, data, nomeArquivo);
        byte[] bytesArquivo = file.getBytes();
        String arquivoInserido = s3Service.uploadArquivo(nomeBucket, pathAquivo, bytesArquivo, extensaoArquivo);
        insereArquivoPontoUsuario(arquivoInserido, idFuncionario, data);

        return arquivoInserido;
    }

    @Override
    public String downloadAnexo(String nomeArquivo, String idFuncionario, LocalDate data) throws IOException {
        String key = geraKeyAnexo(idFuncionario, data, nomeArquivo);
        return s3Service.obterUrlDownload(nomeBucket, key);
    }

    @Override
    public String apagarAnexo(String nomeArquivo, String idFuncionario, LocalDate data) {
        String key = geraKeyAnexo(idFuncionario, data, nomeArquivo);
        String arquivoInserido = s3Service.deleteArquivo(nomeBucket, key);
        removeArquivoPontoUsuario(arquivoInserido, idFuncionario, data);
        return "OK";
    }

    @Override
    public String aprovarPonto(LocalDate data, String idFuncionario, EStatusPonto situacao) {
        PontoUsuario pontoUsuario = pontoUsuarioRepository.buscarPorUsuarioEData(Long.parseLong(idFuncionario), data);
        pontoUsuario.setSituacao(situacao.getStatus());

        pontoUsuarioRepository.save(pontoUsuario);
        return "OK";
    }

    @Override
    public String ajustePontoEmpregador(HorariosAlteracaoDTO horariosAlteracaoDTO) {
        PontoUsuario pontoUsuario = pontoUsuarioRepository.buscarPorUsuarioEData(
                Long.valueOf(horariosAlteracaoDTO.getIdFuncionario()),
                horariosAlteracaoDTO.getData());

        List<HorarioAlterado> horariosAlterados = gerarHorariosAlterados(pontoUsuario, horariosAlteracaoDTO);
        horariosAlterados.forEach(h -> pontoUsuario.getHorariosAlterados().add(h));
        Justificativa justificativa = new Justificativa(
                horariosAlteracaoDTO.getJustificativaEmpregador(),
                buscaDataHoraAgora(),
                ADMIN);
        pontoUsuario.getJustificativas().add(justificativa);
        pontoUsuario.setSituacao(EStatusPonto.PENDENTE_FUNCIONARIO.getStatus());
        pontoUsuarioRepository.save(pontoUsuario);

        return "OK";
    }

    @Override
    public String confirmaAlteracaoPontoFuncionario(LocalDate data, String idFuncionario) {
        PontoUsuario pontoUsuario = pontoUsuarioRepository.buscarPorUsuarioEData(Long.valueOf(idFuncionario), data);
        pontoUsuario.setCienciaFuncionarioPontoAlterado(Boolean.TRUE);
        pontoUsuario.setDataHoraCienciaFuncionarioPontoAlterado(buscaDataHoraAgora());
        pontoUsuario.setSituacao(EStatusPonto.APROVADO.getStatus());
        pontoUsuarioRepository.save(pontoUsuario);

        return "OK";
    }

    @Override
    public String solicitarAjustePonto(JustificativaUsuarioDTO justificativaUsuarioDTO) {
        Long idUsuario = Long.valueOf(justificativaUsuarioDTO.getIdUsuario());
        String nomeUsuario = usuarioService.buscaNomeUsuario(idUsuario).getUsername();
        LocalDate data = justificativaUsuarioDTO.getData();
        PontoUsuario pontoUsuario = pontoUsuarioRepository.buscarPorUsuarioEData(idUsuario, data);

        Justificativa justificativa = new Justificativa(justificativaUsuarioDTO.getJustificativa(), buscaDataHoraAgora(), nomeUsuario);
        pontoUsuario.getJustificativas().add(justificativa);
        pontoUsuario.setSituacao(EStatusPonto.PENDENTE_EMPREGADOR.getStatus());

        pontoUsuarioRepository.save(pontoUsuario);

        return "OK";
    }

    private void insereArquivoPontoUsuario(String arquivoInserido, String idFuncionario, LocalDate data) {
        PontoUsuario pontoUsuario = pontoUsuarioRepository.buscarPorUsuarioEData(Long.parseLong(idFuncionario), data);
        pontoUsuario.getAnexos().add(arquivoInserido);

        pontoUsuarioRepository.save(pontoUsuario);
    }

    private void removeArquivoPontoUsuario(String arquivoInserido, String idFuncionario, LocalDate data) {
        PontoUsuario pontoUsuario = pontoUsuarioRepository.buscarPorUsuarioEData(Long.parseLong(idFuncionario), data);
        pontoUsuario.getAnexos().remove(arquivoInserido);

        pontoUsuarioRepository.save(pontoUsuario);
    }

    private static PontoUsuario geraPontoUsuario(PontoUsuarioRegistroDTO pontoUsuarioRegistroDTO) {
        PontoUsuarioPK pontoUsuarioPK =
                new PontoUsuarioPK(
                        Long.parseLong(pontoUsuarioRegistroDTO.getIdUsuario()),
                        pontoUsuarioRegistroDTO.getPonto().getData());

        return new PontoUsuario(
                pontoUsuarioPK,
                pontoUsuarioRegistroDTO.getPonto().getEntrada(),
                pontoUsuarioRegistroDTO.getPonto().getInicioAlmoco(),
                pontoUsuarioRegistroDTO.getPonto().getFimAlmoco(),
                pontoUsuarioRegistroDTO.getPonto().getSaida(),
                null,
                EStatusPonto.PENDENTE_EMPREGADOR.getStatus());
    }

}

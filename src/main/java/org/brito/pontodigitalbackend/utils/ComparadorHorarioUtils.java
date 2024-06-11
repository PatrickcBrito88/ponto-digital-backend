package org.brito.pontodigitalbackend.utils;

import org.brito.pontodigitalbackend.domain.HorarioAlterado;
import org.brito.pontodigitalbackend.domain.PontoUsuario;
import org.brito.pontodigitalbackend.dtos.HorariosAlteracaoDTO;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.brito.pontodigitalbackend.utils.DataHoraUtils.buscaDataHoraAgora;

public class ComparadorHorarioUtils {


    public static List<HorarioAlterado> gerarHorariosAlterados(PontoUsuario pontoUsuario, HorariosAlteracaoDTO horariosAlteracaoDTO) {

        List<HorarioAlterado> horariosAlterados = new ArrayList<>();
        if (Objects.isNull(pontoUsuario.getEntrada())
                || !pontoUsuario.getEntrada().equals(horariosAlteracaoDTO.getEntrada())) {
            String ENTRADA = "entrada";
            HorarioAlterado horarioAlterado =
                    criarHorarioAlterado(
                            pontoUsuario.getEntrada(),
                            horariosAlteracaoDTO.getEntrada(),
                            horariosAlteracaoDTO.getJustificativaEmpregador(), ENTRADA);
            pontoUsuario.setEntrada(horariosAlteracaoDTO.getEntrada());
            horariosAlterados.add(horarioAlterado);
        }

        if (Objects.isNull(pontoUsuario.getInicioAlmoco())
                || !pontoUsuario.getInicioAlmoco().equals(horariosAlteracaoDTO.getInicioAlmoco())) {
            String INICIO_ALMOCO = "inicio_almoco";
            HorarioAlterado horarioAlterado = criarHorarioAlterado(
                    pontoUsuario.getInicioAlmoco(),
                    horariosAlteracaoDTO.getInicioAlmoco(),
                    horariosAlteracaoDTO.getJustificativaEmpregador(),
                    INICIO_ALMOCO);
            pontoUsuario.setInicioAlmoco(horariosAlteracaoDTO.getInicioAlmoco());
            horariosAlterados.add(horarioAlterado);
        }

        if (Objects.isNull(pontoUsuario.getFimAlmoco())
                || !pontoUsuario.getFimAlmoco().equals(horariosAlteracaoDTO.getFimAlmoco())) {
            String FINAL_ALMOCO = "final_almoco";
            HorarioAlterado horarioAlterado = criarHorarioAlterado(
                    pontoUsuario.getFimAlmoco(),
                    horariosAlteracaoDTO.getFimAlmoco(),
                    horariosAlteracaoDTO.getJustificativaEmpregador(),
                    FINAL_ALMOCO);
            pontoUsuario.setFimAlmoco(horariosAlteracaoDTO.getFimAlmoco());
            horariosAlterados.add(horarioAlterado);
        }

        if (Objects.isNull(pontoUsuario.getSaida())
                || !pontoUsuario.getSaida().equals(horariosAlteracaoDTO.getSaida())) {
            String SAIDA = "saida";
            HorarioAlterado horarioAlterado = criarHorarioAlterado(
                    pontoUsuario.getSaida(),
                    horariosAlteracaoDTO.getSaida(),
                    horariosAlteracaoDTO.getJustificativaEmpregador(),
                    SAIDA);
            pontoUsuario.setSaida(horariosAlteracaoDTO.getSaida());
            horariosAlterados.add(horarioAlterado);
        }

        return horariosAlterados;
    }

    private static HorarioAlterado criarHorarioAlterado(LocalTime horarioFuncionario,
                                                        LocalTime horarioAjustado,
                                                        String justificativa,
                                                        String periodo) {
        HorarioAlterado horarioAlterado = new HorarioAlterado();
        horarioAlterado.setHorarioInicial(horarioFuncionario);
        horarioAlterado.setHorarioAjustado(horarioAjustado);
        horarioAlterado.setJustificativa(justificativa);
        horarioAlterado.setPeriodo(periodo);
        horarioAlterado.setHorarioAlteracao(buscaDataHoraAgora());
        return horarioAlterado;
    }

}

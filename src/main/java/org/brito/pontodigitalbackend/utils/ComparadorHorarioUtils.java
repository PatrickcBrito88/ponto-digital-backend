package org.brito.pontodigitalbackend.utils;

import org.brito.pontodigitalbackend.domain.HorarioAlterado;
import org.brito.pontodigitalbackend.domain.PontoUsuario;
import org.brito.pontodigitalbackend.dtos.HorariosAlteracaoDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class ComparadorHorarioUtils {


    public static HorarioAlterado gerarHorarioAlterado(PontoUsuario pontoUsuario, HorariosAlteracaoDTO horariosAlteracaoDTO) {
        if (pontoUsuario.getEntrada() != null && !pontoUsuario.getEntrada().equals(horariosAlteracaoDTO.getEntrada())) {
            String ENTRADA = "entrada";
            HorarioAlterado horarioAlterado =
                    criarHorarioAlterado(
                            pontoUsuario.getEntrada(),
                            horariosAlteracaoDTO.getEntrada(),
                            horariosAlteracaoDTO.getJustificativaEmpregador(), ENTRADA);
            pontoUsuario.setEntrada(horariosAlteracaoDTO.getEntrada());
            return horarioAlterado;
        }

        if (pontoUsuario.getInicioAlmoco() != null && !pontoUsuario.getInicioAlmoco().equals(horariosAlteracaoDTO.getInicioAlmoco())) {
            String INICIO_ALMOCO = "inicio_almoco";
            HorarioAlterado horarioAlterado = criarHorarioAlterado(
                    pontoUsuario.getInicioAlmoco(),
                    horariosAlteracaoDTO.getInicioAlmoco(),
                    horariosAlteracaoDTO.getJustificativaEmpregador(),
                    INICIO_ALMOCO);
            pontoUsuario.setInicioAlmoco(horariosAlteracaoDTO.getInicioAlmoco());
            return horarioAlterado;
        }

        if (pontoUsuario.getFimAlmoco() != null && !pontoUsuario.getFimAlmoco().equals(horariosAlteracaoDTO.getFimAlmoco())) {
            String FINAL_ALMOCO = "final_almoco";
            HorarioAlterado horarioAlterado = criarHorarioAlterado(
                    pontoUsuario.getFimAlmoco(),
                    horariosAlteracaoDTO.getFimAlmoco(),
                    horariosAlteracaoDTO.getJustificativaEmpregador(),
                    FINAL_ALMOCO);
            pontoUsuario.setFimAlmoco(horariosAlteracaoDTO.getFimAlmoco());
            return horarioAlterado;
        }

        if (pontoUsuario.getSaida() != null && !pontoUsuario.getSaida().equals(horariosAlteracaoDTO.getSaida())) {
            String SAIDA = "saida";
            HorarioAlterado horarioAlterado = criarHorarioAlterado(
                    pontoUsuario.getSaida(),
                    horariosAlteracaoDTO.getSaida(),
                    horariosAlteracaoDTO.getJustificativaEmpregador(),
                    SAIDA);
            pontoUsuario.setSaida(horariosAlteracaoDTO.getSaida());

            return horarioAlterado;
        }

        return null;
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
        horarioAlterado.setHorarioAlteracao(LocalDateTime.now());

        return horarioAlterado;
    }

}

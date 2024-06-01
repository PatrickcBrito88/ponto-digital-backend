package org.brito.pontodigitalbackend.utils;

import java.time.LocalDate;

import static org.brito.pontodigitalbackend.constantes.EmailConstantes.NOME_APLICACAO;

public class S3Utils {

    public static String geraKeyS3(String diretorioServico, String nomeArquivo, String idFuncionario){
        StringBuilder path = new StringBuilder();
        path.append(NOME_APLICACAO);
        path.append("_");
        path.append(diretorioServico);
        path.append("_");
        path.append(idFuncionario);
        return path.toString();
    }

    public static String geraKeyAnexo(String idFuncionario, LocalDate data, String nomeArquivo){
        StringBuilder path = geraPathAnexo(idFuncionario, data);
        path.append(nomeArquivo);

        return path.toString();
    }

    public static StringBuilder geraPathAnexo(String idFuncionario, LocalDate data) {
        int dia = data.getDayOfMonth();
        int mes = data.getMonthValue();
        int ano = data.getYear();
        StringBuilder path = new StringBuilder();
        path.append(idFuncionario);
        path.append("/");
        path.append(ano);
        path.append("/");
        path.append(mes);
        path.append("/");
        path.append(dia);
        path.append("/");
        return path;
    }

}

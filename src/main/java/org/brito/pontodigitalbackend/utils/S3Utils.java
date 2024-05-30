package org.brito.pontodigitalbackend.utils;

import static org.brito.pontodigitalbackend.constantes.EmailConstantes.NOME_APLICACAO;

public class S3Utils {

    public static String geraKeyS3(String diretorioServico, String nomeArquivo, String idFuncionario){
        StringBuilder path = new StringBuilder();
        path.append(NOME_APLICACAO);
        path.append("/");
        path.append(diretorioServico);
        path.append("/");
        path.append(idFuncionario);
        path.append("/");
        path.append(nomeArquivo);

        return path.toString();
    }
}

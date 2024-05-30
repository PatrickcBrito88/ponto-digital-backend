package org.brito.pontodigitalbackend.constantes;

public class EmailConstantes {

    /** DEFAULT */
    private static String PREFIXO = "[NÃO RESPONDA] - SISTEMA PONTO DIGITAL - ";
    public static String PULA_LINHA = "\n";
    public static String ATENCIOSAMENTE = "Atenciosamente";
    public static String GMTB_TEC_ASSINATURA = "GMTB_TEC SISTEMAS";
    public static String NOME_APLICACAO = "ponto-digital";


    /** SENHA TEMPORÁRIA */
    public static String TITULO_SENHA_TEMPORARIA = PREFIXO + "SENHA TEMPORÁRIA";
    public static String INFORMA_SENHA_TEMPORARIA = "Bem vindo ao Sistema Ponto Digital, sua senha temporária é %s.";
    public static String CONCLUSAO_SENHA_TEMPORARIA = "Você deverá alterá-la no primeiro acesso realizado";

    /** S3 */
    public static String DIR_SENHAS_TEMPORARIAS = "senhas-temporarias";
    public static String NOME_ARQUIVO_SENHAS_TEMPORARIAS = "senhaTemporaria.json";
}

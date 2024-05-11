package org.brito.pontodigitalbackend.utils;

import java.security.SecureRandom;

public class GeradorSenha {

    static int TAMANHO_SENHA = 6;

    public static String generatePassword() {
        String charSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < TAMANHO_SENHA; i++) {
            int index = random.nextInt(charSet.length());
            password.append(charSet.charAt(index));
        }

        return password.toString();
    }

}

package br.com.proxmox.backup.server.util;

import java.security.SecureRandom;

public class GeradorUtil {

    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();

    public static String gerarCaracteres(int comprimento) {
        StringBuilder caracterUnico = new StringBuilder(comprimento);
        for (int i = 0; i < comprimento; i++) {
            int index = random.nextInt(CARACTERES.length());
            caracterUnico.append(CARACTERES.charAt(index));
        }
        return caracterUnico.toString();
    }
}

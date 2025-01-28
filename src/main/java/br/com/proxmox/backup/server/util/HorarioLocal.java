package br.com.proxmox.backup.server.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HorarioLocal {

    public static String meuHorario(){
        LocalDateTime horarioLocal = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");

        String horarioFormatado = horarioLocal.format(formatter);

        return horarioFormatado;
    }
}

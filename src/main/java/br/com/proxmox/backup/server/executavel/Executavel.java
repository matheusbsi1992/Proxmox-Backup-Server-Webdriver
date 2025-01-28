package br.com.proxmox.backup.server.executavel;

import static br.com.proxmox.backup.server.executavel.InicializarProcesso.inicializarProcessos;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.time.LocalTime;

public class Executavel {

    public static void main(String[] args) throws InterruptedException {
        //-- Cria uma devida thread
        ScheduledExecutorService agendador = Executors.newScheduledThreadPool(1);

        //-- Inicializar o agendador para rodar o processo a cada 01 minuto
        //-- Analisar se as horas sao a prevista, 07 horas e 30 minutos.
        agendador.scheduleAtFixedRate(() -> {
            LocalTime agora = LocalTime.now();
            if (agora.getHour() == 07 && agora.getMinute() == 30) {
                try {
                    executarProcesso();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 0, 1, TimeUnit.MINUTES);
    }

    public static void executarProcesso() throws InterruptedException {   
        //-- Inicializa os processos de identificacao do WebDriver
        System.out.println("Executando o processo...");
        inicializarProcessos();
    }

}

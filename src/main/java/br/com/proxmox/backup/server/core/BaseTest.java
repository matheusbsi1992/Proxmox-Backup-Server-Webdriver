
package br.com.proxmox.backup.server.core;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static br.com.proxmox.backup.server.util.EnvioEmail.enviarEmailComAnexo;

public class BaseTest {

    public static String nomePasta = "ProxmoxBackupServer";
    private static String nomeArquivo [] = new String[]{"Pag","100","101","102","103","104","105","106","107","108","109","110","111","112","113","114","115","116"};

    private static List<String> arquivosExistentes = new ArrayList<>();


    public static void gerarEvidencias(WebDriver comunicao,String nomeEvidencia)  {

        if(null!=nomeEvidencia && nomeEvidencia.contains(":")){
            nomeEvidencia = nomeEvidencia.replace(":","-");
        }

        TakesScreenshot screenshot = (TakesScreenshot) comunicao;
        File arquivo = screenshot.getScreenshotAs(OutputType.FILE);

        try {

            FileUtils.copyFile(arquivo, new File(nomePasta + File.separator + nomeEvidencia + ".jpg"));

            File arquivoGerado = new File(System.getProperty("user.dir")+File.separator+nomePasta+File.separator+nomeEvidencia+".jpg");

            for (String nomeDoArquivo: nomeArquivo) {
                if(null!=arquivoGerado && nomeDoArquivo.contains(arquivoGerado.getName().substring(0,3))){
                    arquivosExistentes.add(arquivoGerado.toString());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void envioDeArquivosPorEmail(){
           enviarEmailComAnexo(arquivosExistentes);
    }

}
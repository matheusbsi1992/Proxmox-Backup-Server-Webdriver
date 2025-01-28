package br.com.proxmox.backup.server.core;

public class Propriedades {

    public static boolean FINALIZA = true;

    public static Browser browser = Browser.CHROME;

    public static TipoExecucao tipodeExecucao = TipoExecucao.LOCAL;

    public enum Browser {
        CHROME,
        FIREFOX
    }

    public enum TipoExecucao{
        LOCAL,
        GRID,
        NUVEM
    }

}

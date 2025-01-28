package br.com.proxmox.backup.server.executavel;

import br.com.proxmox.backup.server.core.DSL;
import br.com.proxmox.backup.server.util.Sincronismo;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

import static br.com.proxmox.backup.server.comunicacao.AcessoComunicacao.getComunicacaoDriverChrome;
import static br.com.proxmox.backup.server.comunicacao.AcessoComunicacao.getEncerrarNavegadorSessao;
import static br.com.proxmox.backup.server.core.BaseTest.envioDeArquivosPorEmail;
import static br.com.proxmox.backup.server.core.BaseTest.gerarEvidencias;
import static br.com.proxmox.backup.server.util.HorarioLocal.meuHorario;

public class InicializarProcesso {

    private static String link  ="meuLink";
    private static DSL dsl = new DSL();
    private static Sincronismo sincronismo = new Sincronismo();
    private static Boolean valor = false;

    private static void listarVM() throws InterruptedException {
        Actions act = new Actions(getComunicacaoDriverChrome());

        List<WebElement> listaLinhasVMCT = new ArrayList<>();
        List<WebElement> listaLinhasArquivos = new ArrayList<>();
        List<WebElement> listaLinhasComentarios = new ArrayList<>();
        List<String> novoPadraoComentarios = new ArrayList<>();

        listaLinhasComentarios = getComunicacaoDriverChrome().findElements(By.xpath("//div[@class='x-container x-border-item x-box-item x-container-default']//div[@class='x-grid-item-container']//table//tbody//tr//td[@class='x-grid-cell x-grid-td x-grid-cell-gridcolumn-1194 x-unselectable']//span"));

        listaLinhasVMCT = getComunicacaoDriverChrome().findElements(By.xpath("//div[@class='x-container x-border-item x-box-item x-container-default']//div[@class='x-grid-item-container']//table//tbody//tr//td[@class='x-grid-cell x-grid-td x-grid-cell-treecolumn-1193 x-grid-cell-treecolumn x-grid-cell-first x-unselectable']//div//span"));

        String linhasArquivosIdentificados = "//div[@class='x-container x-border-item x-box-item x-container-default']//div[@class='x-grid-item-container']//table//tbody//tr//td[@class='x-grid-cell x-grid-td x-grid-cell-treecolumn-1193  no-leaf-icons x-grid-cell-treecolumn x-grid-cell-first x-unselectable']//span";


        for (WebElement elemento:listaLinhasComentarios) {
            novoPadraoComentarios.add(elemento.getText());
        }

        int i=0;

        for (WebElement web : listaLinhasVMCT) {
            if (web.getText().contains("ct") || web.getText().contains("vm")) {

                act.doubleClick(web).perform();

                gerarEvidencias(getComunicacaoDriverChrome(),web.getText().toUpperCase()+"-"+novoPadraoComentarios.get(i));
                //Thread.sleep(3000);
                listaLinhasArquivos = getComunicacaoDriverChrome().findElements(By.xpath(linhasArquivosIdentificados));

                for (WebElement webElement : listaLinhasArquivos) {
                    act.doubleClick(webElement).perform();
                    //Thread.sleep(3000);
                    gerarEvidencias(getComunicacaoDriverChrome(), webElement.getText().toUpperCase()+"-"+novoPadraoComentarios.get(i));
                    act.doubleClick(webElement).perform();
                }
                act.doubleClick(web).perform();
                i++;
            }
        }
        Thread.sleep(3000);
    }

    private static void identificarPVE(){
        // Identifica o componente respectivo na pagina de Conteudo
        sincronismo.sincronismoExplicitoClicar(By.id("pbsNamespaceSelector-1209-trigger-picker"));
        // Seleciona o idioma respectivo
        dsl.selecionarListarCombo(By.className("x-boundlist-item"),"Valor Campo PVE", "//li[.='Meu PVE']");
    }

    private static void identificarBotaoDeConteudo(){
        sincronismo.sincronismoExplicitoClicar(By.id("tab-1330-btnInnerEl"));
    }

    private static void identificarBotaoDeBackup(){
        sincronismo.sincronismoExplicitoClicar(By.id("ext-element-119"));
    }

    private static void semSubscricaoValida(){
        sincronismo.sincronismoExplicitoClicar(By.id("button-1005-btnWrap"));
    }

    private static void loginProxmoxServer(){
        // Clica no botao de select do Login no Servidor Proxmox Backup
        sincronismo.sincronismoExplicitoClicar(By.id("proxmoxLanguageSelector-1018-trigger-picker"));
        // Seleciona o idioma respectivo
        dsl.selecionarListarCombo(By.className("x-boundlist-item"),"Português Brasileiro - Portuguese (Brazil)","//li[.='Português Brasileiro - Portuguese (Brazil)']");
        // Tempo de decisao da selecao dos campos de escrita
        sincronismo.tempoEspecifico();
        // Informa o username
        dsl.escreve(By.name("username"),"usuario principal");
        // Informa o password
        dsl.escreve(By.name("password"), "senha principal");
        // Botao de Login
        sincronismo.sincronismoExplicitoClicar(By.id("button-1021"));
        // Tempo de decisao para a proxima etapa
        sincronismo.tempoEspecifico();
    }

    private static boolean identificarSeguranca(){
        try{
            valor = null !=  dsl.obterTexto(("details-button")) || (dsl.obterTexto(("details-button"))== null || "".equalsIgnoreCase(dsl.obterTexto(("details-button"))));

            if(valor == Boolean.FALSE){
                return Boolean.FALSE;
            }else{
                sincronismo.sincronismoExplicitoClicar(By.id("details-button"));
                sincronismo.sincronismoExplicitoClicar(By.id("proceed-link"));
            }
        }catch (NoSuchElementException ex){
            ex.getMessage();
        }
        return Boolean.TRUE;
    }

    private static void identificarLink(){
        getComunicacaoDriverChrome().get(link);
    }


    public static void inicializarProcessos() throws InterruptedException {
        System.out.println("Aplicação iniciada com sucesso!");
        //-- Inicializar link
        identificarLink();
        //-- Identificar seguranca
        //valor=identificarSeguranca();
        identificarSeguranca();
        //-- Informar valores campos Login
        loginProxmoxServer();
        //-- Identificador de subscricao valida
        semSubscricaoValida();
        //-- Gerar evidencia da primeira Pagina Principal
        gerarEvidencias(getComunicacaoDriverChrome(),"Pag Principal "+meuHorario()+"-"+"PaginaPrincipal");
        //-- Identificar tres segundos para acessar a pagina de Backup
        Thread.sleep(3000);
        //-- Identificar no Menu o identificador de Backup
        identificarBotaoDeBackup();
        //-- Identificar no Menu o identificador de Conteudo
        identificarBotaoDeConteudo();
        //-- Identificar PVE do Proxmox Server Backup
        identificarPVE();
        Thread.sleep(3000);
        //-- Listar cada VM identificada
        listarVM();
        Thread.sleep(3000);
        //-- Encerrar a comunicacao com o Driver do navegador Chrome
        getEncerrarNavegadorSessao();
        //-- Enviar email com os arquivos existentes
        envioDeArquivosPorEmail();
        System.out.println("Aplicação encerrada com sucesso! \n");
    }
}

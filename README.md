# Proxmox Backup Server WebDriver

Este projeto é uma aplicação Java que utiliza o Selenium WebDriver para automatizar interações com o Proxmox Backup Server. Ele também inclui dependências para manipulação de arquivos (commons-io) 
e envio de e-mails (javax.mail).

# Tecnologias Utilizadas

    Java JDK 22: O projeto é compilado e executado com JDK 22.

    Selenium WebDriver (v4.25.0): Para automação de navegadores.

    Apache Commons IO (v2.16.1): Para manipulação de arquivos e diretórios.

    JavaMail API (v1.6.2): Para envio de e-mails.

# Estrutura do Projeto

O projeto é gerenciado pelo Maven e inclui os seguintes plugins:

    maven-shade-plugin: Para criar um JAR com todas as dependências encapsuladas.

    maven-jar-plugin: Para gerar o JAR executável com o manifesto configurado.

    maven-compiler-plugin: Para garantir a compilação com JDK 22.

# Como Executar o Projeto

Pré-requisitos

    JDK 22 instalado.

    Maven instalado.

Passos para Execução

    Clone o repositório:
    bash
    Copy

    git clone https://github.com/seu-usuario/proxmox-backup-server-webdriver.git

    Navegue até o diretório do projeto:
    bash
    Copy

    cd proxmox-backup-server-webdriver

    Compile e empacote o projeto usando o Maven:
    bash
    Copy

    mvn clean package

    Execute o JAR gerado:
    bash
    Copy

    java -jar target/proxmox-backup-server-webdriver-1.0.jar

# Configuração

Certifique-se de que o arquivo pom.xml está configurado corretamente com as dependências necessárias. A classe principal do projeto é br.com.proxmox.backup.server.executavel.Executavel.

# Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests.
Licença

Este projeto está licenciado sob a MIT License.

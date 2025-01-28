package br.com.proxmox.backup.server.util;

/*import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;*/

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.io.File;
import java.util.List;
import java.util.Properties;



public class EnvioEmail {

    public static void enviarEmailComAnexo(List<String> caminhoArquivos) {
        // Configurações do servidor SMTP
        String host =       "smtp.gmail.com";
        String port =       "465";
        String username =   "tiahssergipe@gmail.com";
        String password =   "jtfe jmcs rfuk pqcc";

        // Informações do email
        String fromEmail =   "tiahssergipe@gmail.com";
        String toEmail  =    "ti@ahs.org.br";
        String subject  =    "Backup Status (server.ahs): backup successful";
        String body     =    "Imagens de gerenciamento do Backup (server.ahs) \n";

        try {
            // Configuração do SMTP
            Properties prop = new Properties();
            prop.put("mail.smtp.host", host);
            prop.put("mail.smtp.port", port);
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.socketFactory.port", port);
            prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            // Autenticação
            Session session = Session.getInstance(prop,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication( username,password );
                }
            });

            // Construção do email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);

            // Corpo principal
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            // Criação do Multipart para anexos
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Adiciona cada imagem como anexo
            for (String imagePath : caminhoArquivos) {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                attachmentPart.attachFile(new File(imagePath));
                multipart.addBodyPart(attachmentPart);
            }

            // Adiciona o conteúdo ao email
            message.setContent(multipart);

            // Envia o email
            Transport.send(message);
            System.out.println("Email enviado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
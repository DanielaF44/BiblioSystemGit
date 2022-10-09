package com.example.bibliosystem.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


/**
 * Service d'envoi d'email pour job de relance
 */
@Component
public class EmailSenderService{

    //TODO : erreur d'autowire non gérée
    @Autowired
    private JavaMailSender javaMailSender;


    /**
     * Méthode d'envoi d'email
     * @param to destination
     * @param subject sujet du mail
     * @param text contenu du mail
     */
    /*public void sendEmail(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("biblio@biblio.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);

    }*/

}

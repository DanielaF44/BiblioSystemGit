package com.example.bibliosystem.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderService {
 /*   @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("biblio@biblio.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send();

    }
*/
}

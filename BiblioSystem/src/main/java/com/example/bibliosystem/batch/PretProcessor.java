package com.example.bibliosystem.batch;
import com.example.bibliosystem.configuration.EmailSenderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.bibliosystem.security.jwt.AuthEntryPointJwt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * Classe de gestion des prêts à l'origine de l'envoi d'un mail de relance
 */
@Component
public class PretProcessor implements ItemProcessor<String, String> {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Autowired
    public JavaMailSender emailSender;

    @Override
    public String process(String data) throws Exception {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("vvincent87@gmail.com");
        message.setSubject("Test Simple Email");
        message.setText("Hello, Im testing Simple Email");

        this.emailSender.send(message);

        return "Email Sent!";
    }

}

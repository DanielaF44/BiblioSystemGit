package com.example.bibliosystem.batch;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.bibliosystem.security.jwt.AuthEntryPointJwt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Classe de gestion des prêts à l'origine de l'envoi d'un mail de relance
 */
public class PretProcessor implements ItemProcessor<String, String> {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

//    @Autowired
//    private JavaMailSender mailSender;

    //stub
    //TODO : ici le code d'envoie d'email quand le probleme aura été identifié

    @Autowired
    EmailSenderService emailService;

    @Override
    public String process(String data) throws Exception {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("biblio@biblio.com");
        message.setTo("vvincent87@gmail.com");
        message.setSubject("subject");
        message.setText("text");
 //       mailSender.send(message);

        //emailService.sendEmail("vvincent87@gmail.com", "subject", "text");
        System.out.println("MyCustomProcessor : Processing data : "+data);
        data = data.toUpperCase();
        logger.info("MyCustomProcessor : Processing data : "+data);
        return data;
    }

}

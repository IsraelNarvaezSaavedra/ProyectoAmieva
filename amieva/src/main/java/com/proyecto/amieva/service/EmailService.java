package com.proyecto.amieva.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    

    @Autowired
    private JavaMailSender mailSender;

    public void enviarEmail(String to, String subject, String body) {
        try {
            
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("narvaez.saavedra.israel@iescamas.es");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            System.err.println("Error enviando email a " + to + ": " + e.getMessage());
			e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperado enviando email: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void enviarEmailSimple(String to, String subject, String texto) {
        try {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("narvaez.saavedra.israel@iescamas.es");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(texto);
            mailSender.send(message);

            System.out.println("Email enviado a: " + to);
        } catch (Exception e) {
            System.err.println("Error email simple a " + to + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}

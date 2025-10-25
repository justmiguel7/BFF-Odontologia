package com.proyecto.odontologiabff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.proyecto.odontologiabff.modelo.Mail;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void enviar(Mail mail) {
        try {
            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setTo(mail.getTo());
            mensaje.setSubject(mail.getSubjet());
            mensaje.setText(mail.getText());
            mensaje.setFrom("dentalhub.organization@gmail.com"); // tu mail desde el que envías

            javaMailSender.send(mensaje);
        } catch (Exception e) {
            System.err.println("Error enviando mail: " + e.getMessage());
        }
    }
}
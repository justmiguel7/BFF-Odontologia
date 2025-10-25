package com.proyecto.odontologiabff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.odontologiabff.modelo.Mail;
import com.proyecto.odontologiabff.service.MailService;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/enviar")
    public String envio(@RequestBody Mail mail) {
        mailService.enviar(mail);
        return "El mail se envió correctamente.";
    }
}
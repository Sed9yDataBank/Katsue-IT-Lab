package com.katsueitlab.katsueserver.mail.controller;

import com.katsueitlab.katsueserver.mail.model.KatsueEmail;
import com.katsueitlab.katsueserver.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    public EmailService emailService;

    @PostMapping("/katsuereply")
    public String index(@RequestBody KatsueEmail email) {
        String response = emailService.sendMail(email);
        return response;
    }
}

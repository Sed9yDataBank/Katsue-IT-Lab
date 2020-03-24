package com.katsueitlab.katsueserver.mail.service.implementation;

import com.katsueitlab.katsueserver.mail.model.Email;
import com.katsueitlab.katsueserver.mail.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImplementation implements com.katsueitlab.katsueserver.mail.service.EmailService{

    @Autowired
    public JavaMailSender javaMailSender;

    @Override
    public Result sendEmail(Email email) {
        Result result = new Result();
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(email.getTo());
            message.setSubject(email.getSubject());
            message.setText(email.getMessage());

            javaMailSender.send(message);

            result.setCode(200);
            result.setNote("Your Email Got Sent Successfully !");
        }catch (Exception exception) {
            result.setCode(404);
            result.setNote("Bad Request ! An Error Accrued Attempting To Send Email " + exception.getMessage());
        }
        return result;
    }
}
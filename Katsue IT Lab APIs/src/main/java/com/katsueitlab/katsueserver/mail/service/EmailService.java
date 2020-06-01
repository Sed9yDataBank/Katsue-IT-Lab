package com.katsueitlab.katsueserver.mail.service;

import com.katsueitlab.katsueserver.mail.model.KatsueEmail;

public interface EmailService {

    public String sendMail(KatsueEmail email);
}

package com.katsueitlab.katsueserver.mail.service;

import com.katsueitlab.katsueserver.mail.model.Email;
import com.katsueitlab.katsueserver.mail.model.Result;

public interface EmailService {
    Result sendEmail(Email email);
}

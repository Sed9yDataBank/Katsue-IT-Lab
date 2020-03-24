package com.katsueitlab.katsueserver.mail.model;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class Email implements Serializable {

    @NotBlank
    private String to;
    @NotBlank
    private String subject;
    @NotBlank
    private String message;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Email{" +
                "To='" + to + '\'' +
                ", Subject='" + subject + '\'' +
                ", Message='" + message + '\'' +
                '}';
    }
}

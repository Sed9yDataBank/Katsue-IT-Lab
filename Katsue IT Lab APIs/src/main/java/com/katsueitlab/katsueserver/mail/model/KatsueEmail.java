package com.katsueitlab.katsueserver.mail.model;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class KatsueEmail implements Serializable {

    @NotBlank
    private String fromName;
    @NotBlank
    private String fromEmail;
    @NotBlank
    private String toName;
    @NotBlank
    private String toEmail;
    @NotBlank
    private String subject;
    @NotBlank
    private String message;

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
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
                "fromName='" + fromName + '\'' +
                ", fromEmail='" + fromEmail + '\'' +
                ", toName='" + toName + '\'' +
                ", toEmail='" + toEmail + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

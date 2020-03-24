package com.katsueitlab.katsueserver.login.beans;

public class AuthenticationBean {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AuthenticationBean (String message) {

        this.message = message;
    }

    @Override
    public String toString() {

        return String.format("Welcome Admin Bean [message=%s]", message);
    }
}

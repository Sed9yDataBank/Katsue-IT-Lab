package com.katsueitlab.katsueserver.login.controller;

import com.katsueitlab.katsueserver.login.beans.AuthenticationBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    @GetMapping(path = "/auth")
    public AuthenticationBean adminBean() {

        return new AuthenticationBean("Success ! Hi Katsue Admin");

    }
}
package com.katsueitlab.katsueserver.login.controller;

import com.katsueitlab.katsueserver.login.beans.Salute;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@CrossOrigin (origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class AdminController {

    private static final String auto = "Welcome, %s !";
    private final AtomicLong count = new AtomicLong();

    @RequestMapping("/salute")
    public Salute salute(@RequestParam (value = "username", defaultValue = "admin") String name) {

        return new Salute(count.incrementAndGet(), String.format(auto, name));
    }
}

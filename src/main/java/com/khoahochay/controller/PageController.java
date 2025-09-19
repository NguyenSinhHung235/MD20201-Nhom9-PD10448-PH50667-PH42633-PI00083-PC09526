package com.khoahochay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/account")
    public String account() {
        return "account";
    }

    @GetMapping("/courses")
    public String courses() {
        return "courses";
    }

    @GetMapping("/lessons")
    public String lessons() {
        return "lessons";
    }

    @GetMapping("/payments")
    public String payments() {
        return "payments";
    }

    @GetMapping("/stats")
    public String stats() {
        return "stats";
    }
}

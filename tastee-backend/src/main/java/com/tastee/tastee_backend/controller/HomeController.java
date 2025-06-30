package com.tastee.tastee_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class HomeController {

    @GetMapping("/")
    public String getMethodName() {
        return "index.html";
    }
    @GetMapping("/protected")
    public String getProtected() {
        return "index.html";
    }
    
}

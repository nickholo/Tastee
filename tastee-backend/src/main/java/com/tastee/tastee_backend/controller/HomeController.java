package com.tastee.tastee_backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {

    // Eventually will return homepage
    @GetMapping("/")
    public String getMethodName() {
        System.out.println("Homepage");
        return "index.html";
    }
    @GetMapping("/protected")
    public String getProtected() {
        return "index.html";
    }
    
}

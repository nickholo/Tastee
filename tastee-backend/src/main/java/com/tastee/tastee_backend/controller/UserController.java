package com.tastee.tastee_backend.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tastee.tastee_backend.beans.Users;
import com.tastee.tastee_backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class UserController {

    @Autowired
    private UserService service;

    
    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        // Here you would typically save the user to the database
        // For now, we just return the user object
        System.out.println("User registered: " + user);
        return service.register(user);
    }

    @GetMapping("/register")
    public String getMethodName(@RequestParam String param) {
        return "Sucess";
    }
    

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        return service.verify(user);
    }

}

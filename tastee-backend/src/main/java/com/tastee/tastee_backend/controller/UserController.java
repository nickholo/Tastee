package com.tastee.tastee_backend.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tastee.tastee_backend.beans.Follow;
import com.tastee.tastee_backend.beans.Users;
import com.tastee.tastee_backend.service.FollowService;
import com.tastee.tastee_backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private FollowService followService;

    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        System.out.println("User registered: " + user);
        return service.register(user);
    }

    @GetMapping("/register")
    public String getMethodName(@RequestParam String param) {
        return "Sucess";
    }
    

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        System.out.println("User login attempt: " + user);
        return service.verify(user);
    }

    // Follow a user
    @PostMapping("/users/{id}/follow")
    public Follow follow(@PathVariable String id, @RequestBody User entity) {
        int followerId = Integer.parseInt(id);
        
        return followService.followUser(followerId);
    }

    // Unfollow a user
    @DeleteMapping("/users/{id}/unfollow")
    public String unfollow(@PathVariable String id, @RequestBody String entity) {
        //TODO: process POST request
        int followedId = Integer.parseInt(id);
        return followService.unfollowUser(followedId);
    }

    // Get list of users followed
    @GetMapping("/users/{id}/following")
    public String getFollowing(@PathVariable String id) {
        //TODO: process GET request

        return "List of users followed by user " + id;
    }
    // Get list of followers
    @GetMapping("/users/{id}/followers")
    public String getFollowers(@PathVariable String id) {
        //TODO: process GET request

        return "List of followers for user " + id;
    }

}

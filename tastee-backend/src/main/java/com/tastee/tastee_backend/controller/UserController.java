package com.tastee.tastee_backend.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tastee.tastee_backend.beans.Follow;
import com.tastee.tastee_backend.beans.Users;
import com.tastee.tastee_backend.service.FollowService;
import com.tastee.tastee_backend.service.UserService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Map<String, String>>login(@RequestBody Users user){
        System.out.println("User login attempt: " + user);
        String token = service.verify(user);

        //Wrap the token in a JSON response
        return ResponseEntity.ok(Map.of("token", token));
    }

    // Follow a user
    @PostMapping("/users/{id}/follow")
    public Follow follow(@PathVariable String id, @RequestBody User entity) {
        Long followerId = Long.parseLong(id);
        
        return followService.followUser(followerId);
    }

    // Unfollow a user
    @DeleteMapping("/users/{id}/unfollow")
    public String unfollow(@PathVariable String id, @RequestBody String entity) {
        Long followedId = Long.parseLong(id);
        return followService.unfollowUser(followedId);
    }

    // Get list of users followed
    @GetMapping("/users/{id}/followed")
    public String getFollowing(@PathVariable String id) {

        return followService.getFollowed(Integer.parseInt(id)).toString();
    }
    // Get list of followers
    @GetMapping("/users/{id}/followers")
    public String getFollowers(@PathVariable String id) {

        return followService.getFollowers(Integer.parseInt(id)).toString();
    }

}

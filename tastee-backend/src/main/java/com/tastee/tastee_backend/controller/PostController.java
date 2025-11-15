package com.tastee.tastee_backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.tastee.tastee_backend.beans.Post;
import com.tastee.tastee_backend.beans.UserPrincipal;
import com.tastee.tastee_backend.beans.Users;
import com.tastee.tastee_backend.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class PostController {

    @Autowired
    private PostService postService;

    // Post a new recipe
    @PostMapping("/post")
    public String postRecipe(@RequestBody Post post) {
        
        postService.createPost(post);
        return "posted bruh";
    }

    // Get a recipe by ID
    @GetMapping("/post/{id}")
    public Post getPostById(@PathVariable int id) {
        return postService.getPostById(id);
    }
    

    // Delete a recipe
    @DeleteMapping("/post/{id}")
    public String deleteRecipe(@PathVariable int id) {
        postService.deletePost(id);
        return "deleted bruh";
    }

    // Update a recipe
    @PostMapping("/post/{id}")
    public String updateRecipe(@PathVariable int id, @RequestBody Post post) {
        postService.updatePost(id, post);
        return "updated bruh";
    }

    @GetMapping("/post/user")
    public String getPostsByUser(@RequestParam String username) {
        return postService.getPostsByUser();
    }
    

}

package com.tastee.tastee_backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.tastee.tastee_backend.beans.Post;
import com.tastee.tastee_backend.beans.UserPrincipal;
import com.tastee.tastee_backend.beans.Users;
import com.tastee.tastee_backend.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/post")
    public String postRecipe(@RequestBody Post post) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Users user = userPrincipal.getUser();
        post.setAuthor(user);
        postService.createPost(post);
        return "posted bruh";
    }

    

}

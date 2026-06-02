package com.tastee.tastee_backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.tastee.tastee_backend.beans.Post;
import com.tastee.tastee_backend.beans.Users;
import com.tastee.tastee_backend.service.PostService;
import com.tastee.tastee_backend.service.MyUserDetailsService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.context.SecurityContextHolder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class PostController {
    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService postService;

    // Post a new recipe
    @PostMapping("/post")
    public String postRecipe(@RequestBody Post post) {
        // Get authenticated user from security context
        var auth = SecurityContextHolder.getContext().getAuthentication();

        // If User exists and is authenticated, set as author of the post
        if (auth != null && auth.isAuthenticated() && auth.getPrincipal() instanceof Users) {
            Users author = (Users) auth.getPrincipal();
            post.setAuthor(author);
            log.info("Creating post: {} by user: {}", post.getTitle(), author.getUsername());
        } else {
            log.warn("No authenticated user found, post author will be null");
        }

        postService.createPost(post);
        return "posted bruh";
    }

    // Get a recipe by ID
    @GetMapping("/post/{id}")
    public Post getPostById(@PathVariable int id) {
        return postService.getPostById(id);
    }

    // Get random recipe
    @GetMapping("/post/random")
    public List<Post> getRandomPost() {
        Post post = postService.getRandomPost();
        if (post == null) {
            return List.of();
        }
        return List.of(post);
    }

    // Delete a recipe
    @DeleteMapping("/post/{id}")
    public String deleteRecipe(@PathVariable int id) {
        postService.deletePost(id);
        return "deleted bruh";
    }

    // Update a recipe
    @PostMapping("/post/{id}")
    public String updateRecipe(@PathVariable Long id, @RequestBody Post post) {
        postService.updatePost(id, post);
        return "updated bruh";
    }

    // Get all recipes by a user
    @GetMapping("/post/user")
    public List<Post> getPostsByUser(@RequestParam String username) {
        return postService.getPostsByUser();
    }

    // Post Interactions

    // Like a recipe
    @PostMapping("/post/{id}/like")
    public ResponseEntity<Map<String, Object>> likePost(@PathVariable int id) {
        log.info("Liking post with id: {}", id);
        return postService.likePost(id);

    }

    // Unlike a recipe
    @PostMapping("/post/{id}/unlike")
    public ResponseEntity<String> unlikePost(@PathVariable int id) {

        return postService.unlikePost(id);
    }

}

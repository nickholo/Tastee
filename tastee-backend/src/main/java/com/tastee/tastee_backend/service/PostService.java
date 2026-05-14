package com.tastee.tastee_backend.service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tastee.tastee_backend.beans.Post;
import com.tastee.tastee_backend.beans.UserPrincipal;
import com.tastee.tastee_backend.beans.Users;
import com.tastee.tastee_backend.database.PostRepo;
import com.tastee.tastee_backend.database.UserRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;
    
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private Environment environment;

    // convention: pass the current class so logs show where they came from
    private static final Logger log = LoggerFactory.getLogger(PostService.class);

    private Users getCurrentUser(){

        Users user = new Users();
        UserPrincipal userPrincipal = null;

        // If Dev profile active use dummy user
        if (environment.matchesProfiles("dev")) {
            // Check if dev user already exists in database
            user = userRepo.findByUsername("devuser");
            if (user == null) {
                // Create and save dev user if it doesn't exist
                user = new Users();
                user.setId(1);
                user.setUsername("devuser");
                user.setPassword("devpass"); // dummy password
                user = userRepo.save(user);
                log.info("Created and saved dev user: {}", user.getUsername());
            } else {
                log.info("Using existing dev user: {}", user.getUsername());
            }
            return user;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userPrincipal = (UserPrincipal) authentication.getPrincipal();
        user = userPrincipal.getUser();
        
    
        return user;
    }
    public Post createPost(Post post) {
        Users user = getCurrentUser();
        post.setAuthor(user);
        log.info("Creating post: {} by user: {}", post.getTitle(), user.getUsername());
        return postRepo.save(post);
        
    }

    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    public Post getRandomPost() {
        long postCount = postRepo.count();
        if (postCount == 0) {
            return null;
        }

        // Generate a random index and retrieve the post at that index
        long randomIndex = ThreadLocalRandom.current().nextLong(postCount);
        return postRepo.findAll(PageRequest.of((int) randomIndex, 1)).stream().findFirst().orElse(null);
    }

    public Post getPostById(int id) {
        return postRepo.findById(id).orElse(null);
    }

    public Post updatePost(int id, Post post) {
        // TODO Update and change posts
        post.setId(id);
        return postRepo.save(post);
    }

    public void deletePost(int id) {
        postRepo.deleteById(id);
    }

    public List<Post> getPostsByUser() {
        Users user = getCurrentUser();
        List<Post> posts = postRepo.findByAuthor(user);
        return posts;
    }
}

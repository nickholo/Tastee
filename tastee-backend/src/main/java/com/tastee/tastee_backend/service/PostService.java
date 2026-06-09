package com.tastee.tastee_backend.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tastee.tastee_backend.beans.InteractionType;
import com.tastee.tastee_backend.beans.Post;
import com.tastee.tastee_backend.beans.PostInteraction;
import com.tastee.tastee_backend.beans.UserPrincipal;
import com.tastee.tastee_backend.beans.Users;
import com.tastee.tastee_backend.database.PostInteractionRepo;
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
    private PostInteractionRepo postInteractionRepo;

    @Autowired
    private Environment environment;

    // convention: pass the current class so logs show where they came from
    private static final Logger log = LoggerFactory.getLogger(PostService.class);

    private Users getCurrentUser() {

        Users user = new Users();
        UserPrincipal userPrincipal = null;

        // If Dev profile active use dummy user
        if (environment.matchesProfiles("dev")) {
            // Check if dev user already exists in database
            user = userRepo.findByUsername("devuser");
            if (user == null) {
                // Create and save dev user if it doesn't exist
                user = new Users();
                user.setId(Long.valueOf(1)); // Set a fixed ID for the dev user
                user.setUsername("devuser");
                user.setPassword("devpass"); // dummy password
                user = userRepo.save(user);
                log.info("Created and saved dev user: {}", user.getUsername());
            } else {
                log.info("Using existing dev user: {}", user.getUsername());
            }
            return user;
        }

        // In production, get authenticated user from security context
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

    public Post getPostById(Long id) {
        return postRepo.findById(id).orElse(null);
    }

    public Post updatePost(Long id, Post post) {
        // TODO Update and change posts
        post.setId(id);
        return postRepo.save(post);
    }

    public ResponseEntity<String> deletePost(Long id) {
        postRepo.deleteById(id);

        return ResponseEntity.ok("Deleted post with id: " + id);
    }

    public List<Post> getPostsByUser() {
        Users user = getCurrentUser();
        List<Post> posts = postRepo.findByAuthor(user);
        return posts;
    }

    public ResponseEntity<Map<String, Object>> likePost(Long id) {
        // Get the post by ID
        Post post = postRepo.findById(id).orElse(null);

        // If the post exists create a post ineraction with the logged in user.
        if (post != null) {
            PostInteraction like = new PostInteraction();
            like.setPost(post);
            like.setUser(getCurrentUser());
            like.setType(InteractionType.LIKE);
            postInteractionRepo.save(like);
        }
        return ResponseEntity.ok(Map.<String, Object>of(
                "message", "liked post",
                "id", id));
    }

    public ResponseEntity<String> unlikePost(Long id) {
        Users user = getCurrentUser();
        Post post = postRepo.findById(id).orElse(null);
        postInteractionRepo.deleteByUserIdAndPostId(
                user.getId(),
                post.getId());
        return ResponseEntity.ok("Disliked post with id: " + id);
    }

}

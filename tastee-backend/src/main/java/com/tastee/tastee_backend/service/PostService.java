package com.tastee.tastee_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tastee.tastee_backend.beans.Post;
import com.tastee.tastee_backend.beans.UserPrincipal;
import com.tastee.tastee_backend.beans.Users;
import com.tastee.tastee_backend.database.PostRepo;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    private Users getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Users user = userPrincipal.getUser();
        return user;
    }
    public Post createPost(Post post) {
        Users user = getCurrentUser();
        post.setAuthor(user);
        System.out.println("Creating post: " + post);
        return postRepo.save(post);
        
    }

    public List<Post> getAllPosts() {
        return postRepo.findAll();
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

    public String getPostsByUser() {
        Users user = getCurrentUser();
        List<Post> posts = postRepo.findByAuthor(user);
        return posts.toString();
    }
}

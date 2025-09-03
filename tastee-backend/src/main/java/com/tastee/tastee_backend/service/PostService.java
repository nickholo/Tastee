package com.tastee.tastee_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tastee.tastee_backend.beans.Post;
import com.tastee.tastee_backend.database.PostRepo;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    public Post createPost(Post post) {
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
        post.setId(id);
        return postRepo.save(post);
    }

    public void deletePost(int id) {
        postRepo.deleteById(id);
    }

}

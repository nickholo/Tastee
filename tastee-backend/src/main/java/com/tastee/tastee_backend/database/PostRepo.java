package com.tastee.tastee_backend.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tastee.tastee_backend.beans.Post;
import com.tastee.tastee_backend.beans.Users;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByAuthor(Users author);
    
    
}

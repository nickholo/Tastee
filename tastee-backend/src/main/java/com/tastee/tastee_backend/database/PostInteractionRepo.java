package com.tastee.tastee_backend.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tastee.tastee_backend.beans.PostInteraction;

public interface PostInteractionRepo extends JpaRepository<PostInteraction, Long> {
	List<PostInteraction> findByUserId(Long followedId);
	List<PostInteraction> findByPostId(Long postId);
	PostInteraction findByUserIdAndPostId(Long userId, Long postId);
	void deleteByUserIdAndPostId(Long userId, Long postId);
}

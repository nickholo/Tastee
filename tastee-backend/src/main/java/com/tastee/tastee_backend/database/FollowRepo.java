package com.tastee.tastee_backend.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tastee.tastee_backend.beans.Follow;
import com.tastee.tastee_backend.beans.FollowId;

@Repository
public interface FollowRepo extends JpaRepository<Follow, FollowId> {
    List<Follow> findByFollowerId(Long followerId);
    List<Follow> findByFollowedId(Long followedId);
    long countByFollowerId(Long followingId);
    long countByFollowedId(Long followedId);
}

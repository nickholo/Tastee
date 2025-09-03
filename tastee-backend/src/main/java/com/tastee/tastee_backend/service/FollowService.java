package com.tastee.tastee_backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.tastee.tastee_backend.beans.Follow;
import com.tastee.tastee_backend.beans.UserPrincipal;
import com.tastee.tastee_backend.database.FollowRepo;

@Service
public class FollowService {

    @Autowired
    private FollowRepo followRepo;

    
    public Object getLoggedInUserDetails() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.isAuthenticated()) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            return (UserDetails) principal; // Cast to UserDetails for custom user information
        } else {
            return principal; // Principal might be a String if no UserDetails is used
        }
    }
    return null;
}

    

    public Follow followUser(int followerId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        int userId = userPrincipal.getId();
        System.out.println(userId);
        Follow follow = new Follow();
        follow.setFollowerId(followerId);
        follow.setFollowedId(userId);
        return followRepo.save(follow);
    }

    public String unfollowUser(int followerId, int followedId) {
        Follow follow = new Follow();
        follow.setFollowerId(followerId);
        follow.setFollowedId(followedId);
        followRepo.delete(follow);
        return "Unfollowed user with ID: " + followedId;
    }

    public Object getFollowers(int userId) {
        return followRepo.findByFollowedId((long) userId);
    }

}

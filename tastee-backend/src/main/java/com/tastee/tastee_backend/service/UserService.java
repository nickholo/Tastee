package com.tastee.tastee_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tastee.tastee_backend.beans.Users;
import com.tastee.tastee_backend.database.UserRepo;
import com.tastee.tastee_backend.dto.ProfileDTO;


@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private FollowService followService;


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);

    public Users register(Users user){
        user.setPassword(encoder.encode(user.getPassword()));

        return repo.save(user);
    }

    public String verify(Users user) {
       Authentication authentication =  authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } else {
            return "Authentication failed";
        }
    }

    public ProfileDTO getProfile(Long userId) {
        Users user = repo.findById(userId).orElse(null);
        if (user == null) {
            return null; 
        }
        ProfileDTO profile = new ProfileDTO();
        profile.setUsername(user.getUsername());
        profile.setBio(user.getBio());
        profile.setProfilePictureUrl(user.getProfilePictureUrl());
        profile.setFollowersCount(followService.countFollowers(userId));
        profile.setFollowingCount(followService.countFollowed(userId));


        return profile;
    }
}

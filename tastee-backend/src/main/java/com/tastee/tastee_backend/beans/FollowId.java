package com.tastee.tastee_backend.beans;

import java.io.Serializable;
import java.util.Objects;

// Class representing the composite key for the Follow entity
public class FollowId implements Serializable {

    private int followerId;
    private int followedId;

    // Deafult Required by JPA
    public FollowId() {};
    public FollowId(int followerId, int followedId) {
        this.followerId = followerId;
    this.followedId = followedId;
    }

    // Getters and Setters
    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }

    public int getFollowedId() {
        return followedId;
    }

    public void setFollowedId(int followedId) {
        this.followedId = followedId;
    }

    // hashCode and equals required by JPA
    @Override
    public int hashCode() {
    return Objects.hash(followerId, followedId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FollowId)) return false;
        FollowId other = (FollowId) obj;
    return followerId == other.followerId && followedId == other.followedId;
    }
}

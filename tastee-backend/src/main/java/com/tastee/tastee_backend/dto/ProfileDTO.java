package com.tastee.tastee_backend.dto;

public class ProfileDTO {
	private String username;
	private String profilePictureUrl;
	private String bio;

	private long followersCount;
	private long followingCount;

	public ProfileDTO() {
	}

	public ProfileDTO(String username, String profilePictureUrl, String bio, long followersCount, long followingCount) {
		this.username = username;
		this.profilePictureUrl = profilePictureUrl;
		this.bio = bio;
		this.followersCount = followersCount;
		this.followingCount = followingCount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProfilePictureUrl() {
		return profilePictureUrl;
	}

	public void setProfilePictureUrl(String profilePictureUrl) {
		this.profilePictureUrl = profilePictureUrl;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public long getFollowersCount() {
		return followersCount;
	}

	public void setFollowersCount(long followersCount) {
		this.followersCount = followersCount;
	}

	public long getFollowingCount() {
		return followingCount;
	}

	public void setFollowingCount(long followingCount) {
		this.followingCount = followingCount;
	}

}

package com.javatpoint.model;

import java.util.List;

public class UserRating {
	private String userId;
	private List<Rating> userRatting;

	public UserRating() {
	}

	public UserRating(String userId, List<Rating> userRatting) {
		super();
		this.userId = userId;
		this.userRatting = userRatting;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Rating> getUserRatting() {
		return userRatting;
	}

	public void setUserRatting(List<Rating> userRatting) {
		this.userRatting = userRatting;
	}

	@Override
	public String toString() {
		return "UserRating [userId=" + userId + ", userRatting=" + userRatting + "]";
	}

}

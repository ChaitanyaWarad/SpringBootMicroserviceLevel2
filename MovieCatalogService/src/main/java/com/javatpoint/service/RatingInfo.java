package com.javatpoint.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.javatpoint.model.Rating;
import com.javatpoint.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class RatingInfo {

	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "fallBackGetUserRatings")
	public UserRating getUserRatings(@PathVariable("restuserid") String restuserid) {
		UserRating userRating = restTemplate.getForObject("http://localhost:9093/ratingsdata/users/" + restuserid,
				UserRating.class);
		System.out.println(userRating);
		return userRating;
	}

	public UserRating fallBackGetUserRatings(@PathVariable("restuserid") String restuserid) {
		UserRating userRating = new UserRating();
		userRating.setUserId(restuserid);
		userRating.setUserRatting(Arrays.asList(new Rating("0", 0)));

		return userRating;
	}

}

package com.javatpoint.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.javatpoint.model.CatalogItem;
import com.javatpoint.model.Movie;
import com.javatpoint.model.Rating;
import com.javatpoint.model.UserRating;
import com.javatpoint.service.MovieInfo;
import com.javatpoint.service.RatingInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder webClientBuilder;

	@Autowired
	private RatingInfo ratingInfo;

	@Autowired
	private MovieInfo movieInfo;

	@RequestMapping("/test")
	public String test() {
		return "Application is working fine !!!";
	}

	// using resttemplate
	@RequestMapping("/{restuserid}")
	@HystrixCommand(fallbackMethod = "fallBackGetCatalog")
	public List<CatalogItem> getCatalogRest(@PathVariable("restuserid") String restuserid) {
		UserRating userRating = ratingInfo.getUserRatings(restuserid);
		return userRating.getUserRatting().stream().map(rating -> movieInfo.getCatalogItem(rating))
				.collect(Collectors.toList());
	}

	public List<CatalogItem> fallBackGetCatalog(@PathVariable("restuserid") String restuserid) {
		return Arrays.asList(new CatalogItem("No Movie", "No Movie", 0));
	}

	
	
	
	// using webclient
	/*
	 * @RequestMapping("user/{userid}") public List<CatalogItem>
	 * getCatalogWebClient(@PathVariable("userid") String userid) {
	 * 
	 * List<Rating> rattings = Arrays.asList(new Rating("1234", 4), new
	 * Rating("5678", 3), new Rating("1234", 4));
	 * 
	 * Movie movie =
	 * webClientBuilder.build().get().uri("http://localhost:9092/movies/movieid").
	 * retrieve() .bodyToMono(Movie.class).block(); System.out.println(movie);
	 * 
	 * List<CatalogItem> list = rattings.stream() .map(ratings -> new
	 * CatalogItem(movie.getName(), "sandalwood samuggling", ratings.getRating()))
	 * .collect(Collectors.toList()); return list;
	 * 
	 * }
	 */

}
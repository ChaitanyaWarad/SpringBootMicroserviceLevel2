package com.javatpoint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.javatpoint.model.Movie;
import com.javatpoint.model.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieController {

	@Value("${api.key}")
	private String apiKey;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/test")
	public String test() {
		return "Application is working fine !!!";
	}

	@RequestMapping("/{movieid}")
	public Movie getMovieInfo(@PathVariable("movieid") String movieid) {
		MovieSummary MovieSummary = restTemplate.getForObject(
				"https://api.themoviedb.org/3/movie/" + movieid + "?api_key=" + apiKey, MovieSummary.class);
		return new Movie(movieid, MovieSummary.getTitle(), MovieSummary.getOverview());
	}

}

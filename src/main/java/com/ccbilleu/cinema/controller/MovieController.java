package com.ccbilleu.cinema.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccbilleu.cinema.db.exception.DaoException;
import com.ccbilleu.cinema.db.model.Movie;
import com.ccbilleu.cinema.dto.MovieDto;
import com.ccbilleu.cinema.service.MovieService;
import com.ccbilleu.cinema.transform.MovieTransformer;

@Controller
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@RequestMapping(value="{cinemaId}", method = RequestMethod.GET)
	public @ResponseBody List<MovieDto> getMovies(@PathVariable Long cinemaId) {
		try {
			final List<Movie> movies = movieService.getMovies(cinemaId);
			final List<MovieDto> movieDtos = new ArrayList<MovieDto>(movies.size());
			for (Movie movie : movies) {
				movieDtos.add(MovieTransformer.transform(movie));
			}
			return movieDtos;
		} catch (DaoException e) {
			final MovieDto movieDto = new MovieDto();
			movieDto.setErrorMessage(e.getMessage());
			return Collections.singletonList(movieDto);
		}
	}
	
	@RequestMapping(value="/futureMovies/{cinemaId}", method = RequestMethod.GET)
	public @ResponseBody List<MovieDto> getFutureMovies(@PathVariable Long cinemaId) {
		try {
			final List<Movie> movies = movieService.getFutureMovies(cinemaId);
			final List<MovieDto> movieDtos = new ArrayList<MovieDto>(movies.size());
			for (Movie movie : movies) {
				movieDtos.add(MovieTransformer.transform(movie));
			}
			return movieDtos;
		} catch (DaoException e) {
			final MovieDto movieDto = new MovieDto();
			movieDto.setErrorMessage(e.getMessage());
			return Collections.singletonList(movieDto);
		}
	}
}
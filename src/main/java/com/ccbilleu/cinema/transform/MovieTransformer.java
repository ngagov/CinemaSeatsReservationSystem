package com.ccbilleu.cinema.transform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.ccbilleu.cinema.DateUtil;
import com.ccbilleu.cinema.db.model.Movie;
import com.ccbilleu.cinema.db.model.MovieShow;
import com.ccbilleu.cinema.dto.MovieDto;
import com.ccbilleu.cinema.dto.MovieShowDto;

public class MovieTransformer {

	public static MovieDto transform(Movie movie) {
		if (movie == null) {
			return null;
		}
		final MovieDto movieDto = new MovieDto();
		movieDto.setId(movie.getId());
		movieDto.setName(movie.getName());
		movieDto.setImageUrl(movie.getImageUrl());
		movieDto.setDescription(movie.getDescription());
		movieDto.setReleaseDate(DateUtil.formatDate(movie.getReleaseDate()));
		final Set<MovieShow> movieShows = movie.getMovieShows();
		if (movieShows != null && !movieShows.isEmpty()) {
			movieDto.setMovieShows(new ArrayList<MovieShowDto>(movieShows.size()));
			for (MovieShow movieShow : movieShows) {
				final MovieShowDto movieShowDto = MovieShowTransformer.transform(movieShow);
				movieDto.getMovieShows().add(movieShowDto);
			}
		}
		List<MovieShowDto> movieShowDtos = movieDto.getMovieShows();
		if (movieShowDtos != null && !movieShowDtos.isEmpty()) {
			Collections.sort(movieDto.getMovieShows());
		}
		return movieDto;
	}
}

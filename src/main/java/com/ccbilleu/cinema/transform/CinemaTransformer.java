package com.ccbilleu.cinema.transform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.ccbilleu.cinema.db.model.Cinema;
import com.ccbilleu.cinema.db.model.Movie;
import com.ccbilleu.cinema.dto.CinemaDto;
import com.ccbilleu.cinema.dto.MovieDto;

public class CinemaTransformer {

	public static CinemaDto transform(Cinema cinema) {
		if (cinema == null) {
			return null;
		}
		final CinemaDto cinemaDto = new CinemaDto();
		cinemaDto.setId(cinema.getId());
		cinemaDto.setName(cinema.getName());
		final Set<Movie> movies = cinema.getMovies();
		if (movies != null && !movies.isEmpty()) {
			final List<MovieDto> movieDtos = new ArrayList<MovieDto>(movies.size());
			for (Movie movie : movies) {
				movieDtos.add(MovieTransformer.transform(movie));
			}
			cinemaDto.setMovies(movieDtos);
			Collections.sort(movieDtos);
		}
		return cinemaDto;
	}
}

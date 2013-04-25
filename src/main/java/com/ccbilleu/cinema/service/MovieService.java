package com.ccbilleu.cinema.service;

import java.util.List;

import com.ccbilleu.cinema.db.exception.DaoException;
import com.ccbilleu.cinema.db.model.Movie;

public interface MovieService {
	List<Movie> getMovies(Long cinemaId) throws DaoException;
	List<Movie> getFutureMovies(Long cinemaId) throws DaoException;
}

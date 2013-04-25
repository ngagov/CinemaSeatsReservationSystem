package com.ccbilleu.cinema.db.dao;

import java.util.List;

import com.ccbilleu.cinema.db.exception.DaoException;
import com.ccbilleu.cinema.db.model.Movie;

public interface MovieDao {

	List<Movie> getMovies(Long cinemaId) throws DaoException;
	
	List<Movie> getFutureMovies(Long cinemaId) throws DaoException;
}

package com.ccbilleu.cinema.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.ccbilleu.cinema.db.dao.MovieDao;
import com.ccbilleu.cinema.db.exception.DaoException;
import com.ccbilleu.cinema.db.model.Movie;
import com.ccbilleu.cinema.service.MovieService;

@Transactional
public class BaseMovieServiceImpl implements MovieService {

	private MovieDao movieDao;
	
	public List<Movie> getMovies(Long cinemaId) throws DaoException {
		return movieDao.getMovies(cinemaId);
	}
	
	public List<Movie> getFutureMovies(Long cinemaId) throws DaoException {
		return movieDao.getFutureMovies(cinemaId);
	}

	@Required
	public void setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
	}
}

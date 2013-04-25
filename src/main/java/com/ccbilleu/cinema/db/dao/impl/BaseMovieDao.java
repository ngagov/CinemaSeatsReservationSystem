package com.ccbilleu.cinema.db.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ccbilleu.cinema.db.dao.MovieDao;
import com.ccbilleu.cinema.db.exception.DaoException;
import com.ccbilleu.cinema.db.model.Cinema;
import com.ccbilleu.cinema.db.model.Movie;

public class BaseMovieDao implements MovieDao {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Movie> getFutureMovies(Long cinemaId) throws DaoException {
		final Cinema cinema = em.find(Cinema.class, cinemaId);
		if (cinema == null) {
			throw new DaoException("Could not find Cinema by pk " + cinemaId);
		}
		return em.createQuery("SELECT m FROM Movie m JOIN Cinema c ON m.cinema.id = c.id WHERE (c.id = :cinemaId) AND m.releaseDate > :date")
			.setParameter("cinemaId", cinemaId).setParameter("date", new Date()).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Movie> getMovies(Long cinemaId) throws DaoException {
		final Cinema cinema = em.find(Cinema.class, cinemaId);
		if (cinema == null) {
			throw new DaoException("Could not find Cinema by pk " + cinemaId);
		}
		return em.createQuery("SELECT m FROM Movie m JOIN Cinema c ON m.cinema.id = c.id WHERE (c.id = :cinemaId) AND m.releaseDate <= :date")
			.setParameter("cinemaId", cinemaId).setParameter("date", new Date()).getResultList();
	}
}

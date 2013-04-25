package com.ccbilleu.cinema.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.ccbilleu.cinema.db.dao.CinemaDao;
import com.ccbilleu.cinema.db.exception.DaoException;
import com.ccbilleu.cinema.db.model.Cinema;
import com.ccbilleu.cinema.service.CinemaService;

public class BaseCinemaServiceImpl implements CinemaService {

	private CinemaDao cinemaDao;
	
	@Transactional
	public Cinema getDefaultCinema() throws DaoException {
		final List<Cinema> cinemas = cinemaDao.getCinemas();
		if (cinemas == null || cinemas.isEmpty()) {
			return null;
		}
		return cinemas.get(0);
	}

	@Required
	public void setCinemaDao(CinemaDao cinemaDao) {
		this.cinemaDao = cinemaDao;
	}
 
	
}

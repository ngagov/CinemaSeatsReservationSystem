package com.ccbilleu.cinema.service;

import com.ccbilleu.cinema.db.exception.DaoException;
import com.ccbilleu.cinema.db.model.Cinema;

public interface CinemaService {
	Cinema getDefaultCinema() throws DaoException;
}

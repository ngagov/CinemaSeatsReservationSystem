package com.ccbilleu.cinema.db.dao;

import java.util.List;

import com.ccbilleu.cinema.db.exception.DaoException;
import com.ccbilleu.cinema.db.model.Cinema;

public interface CinemaDao {
	List<Cinema> getCinemas() throws DaoException;
}

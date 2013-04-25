package com.ccbilleu.cinema.db.dao;

import java.security.Principal;
import java.util.List;

import com.ccbilleu.cinema.db.exception.DaoException;
import com.ccbilleu.cinema.db.model.Seat;
import com.ccbilleu.cinema.dto.MovieShowDto;

public interface SeatDao {
	List<Seat> getSeats(Long theatreId) throws DaoException;
	void updateSeats(MovieShowDto movieShowDto, Principal principal) throws DaoException;
}

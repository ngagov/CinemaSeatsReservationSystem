package com.ccbilleu.cinema.service;

import java.security.Principal;
import java.util.List;

import com.ccbilleu.cinema.db.exception.DaoException;
import com.ccbilleu.cinema.db.model.Seat;
import com.ccbilleu.cinema.dto.MovieShowDto;

public interface SeatService {
	List<Seat> getSeats(Long theatreId) throws DaoException;
	void updateSeats(MovieShowDto movieShowDto, Principal principal) throws DaoException;
}

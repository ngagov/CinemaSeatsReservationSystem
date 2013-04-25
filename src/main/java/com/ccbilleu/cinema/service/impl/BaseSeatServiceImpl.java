package com.ccbilleu.cinema.service.impl;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.ccbilleu.cinema.db.dao.SeatDao;
import com.ccbilleu.cinema.db.exception.DaoException;
import com.ccbilleu.cinema.db.model.Seat;
import com.ccbilleu.cinema.dto.MovieShowDto;
import com.ccbilleu.cinema.service.SeatService;

@Transactional
public class BaseSeatServiceImpl implements SeatService {
	private SeatDao seatDao;
	
	public List<Seat> getSeats(Long theatreId) throws DaoException {
		return seatDao.getSeats(theatreId);
	}

	@Required
	public void setSeatDao(SeatDao seatDao) {
		this.seatDao = seatDao;
	}
	
	public void updateSeats(MovieShowDto movieShowDto, Principal principal) throws DaoException {
		seatDao.updateSeats(movieShowDto, principal);
	}
}

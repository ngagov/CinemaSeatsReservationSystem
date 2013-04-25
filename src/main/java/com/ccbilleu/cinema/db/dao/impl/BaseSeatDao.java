package com.ccbilleu.cinema.db.dao.impl;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ccbilleu.cinema.Constants;
import com.ccbilleu.cinema.db.dao.SeatDao;
import com.ccbilleu.cinema.db.exception.DaoException;
import com.ccbilleu.cinema.db.model.MovieShow;
import com.ccbilleu.cinema.db.model.Seat;
import com.ccbilleu.cinema.db.model.SeatStatus;
import com.ccbilleu.cinema.db.model.Theatre;
import com.ccbilleu.cinema.dto.MovieShowDto;
import com.ccbilleu.cinema.dto.SeatDto;

public class BaseSeatDao implements SeatDao {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Seat> getSeats(Long theatreId) throws DaoException {
		final Theatre theatre = em.find(Theatre.class, theatreId);
		if (theatre == null) {
			throw new DaoException("Could not find Theatre by pk " + theatreId);
		}
		return em.createQuery("SELECT s FROM Seat s JOIN Theatre t ON s.theatre = t.id WHERE (t.id = :theatreId)")
			.setParameter("theatreId", theatreId).getResultList();
	}
	
	public void updateSeats(MovieShowDto movieShowDto, Principal principal) throws DaoException {
		final MovieShow movieShow = em.find(MovieShow.class, movieShowDto.getId());
		updateSeats(movieShow.getSeats(), movieShowDto.getSeats(), principal);
	}
	
	private void updateSeats(Set<Seat> seats, List<SeatDto> seatDtos, Principal principal) {
		for (SeatDto seatDto : seatDtos) {
			if (Constants.STATUS_OCCUPIED_BY_ME_NEW.equals(seatDto.getStatus())) {
				for (Seat seat : seats) {
					if (seat.getId().equals(seatDto.getId())) {
						seat.setSeatStatus(SeatStatus.OCCUPIED);
						seat.setOwner(principal.getName());
						seatDto.setStatus(Constants.STATUS_OCCUPIED_BY_ME);
						em.merge(seat);
					}
				}
			}
		}
	}
}

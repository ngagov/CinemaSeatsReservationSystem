package com.ccbilleu.cinema.transform;

import org.springframework.security.core.context.SecurityContextHolder;

import com.ccbilleu.cinema.Constants;
import com.ccbilleu.cinema.db.model.Seat;
import com.ccbilleu.cinema.dto.SeatDto;

public class SeatTransformer {
	public static SeatDto transform(Seat seat) {
		if (seat == null) {
			return null;
		}
		final SeatDto seatDto = new SeatDto();
		seatDto.setId(seat.getId());
		seatDto.setNumber(seat.getNumber());
		final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		String status = seat.getSeatStatus().toString();
		final String owner = seat.getOwner();
		if (currentUser.equals(owner)) {
			status = Constants.STATUS_OCCUPIED_BY_ME;
		}
		seatDto.setStatus(status);
		return seatDto;
	}
}

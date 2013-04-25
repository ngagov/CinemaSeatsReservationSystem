package com.ccbilleu.cinema.transform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.ccbilleu.cinema.DateUtil;
import com.ccbilleu.cinema.db.model.MovieShow;
import com.ccbilleu.cinema.db.model.Seat;
import com.ccbilleu.cinema.dto.MovieShowDto;
import com.ccbilleu.cinema.dto.SeatDto;

public class MovieShowTransformer {
	
	public static MovieShowDto transform(MovieShow movieShow) {
		if (movieShow == null) {
			return null;
		}
		final MovieShowDto movieShowDto = new MovieShowDto();
		movieShowDto.setId(movieShow.getId());
		movieShowDto.setShowDate(DateUtil.formatDate(movieShow.getShowDate()));
		movieShowDto.setTheatreDto(TheatreTransformer.transform(movieShow.getTheatre()));
		final Set<Seat> seats = movieShow.getSeats();
		if (seats != null && !seats.isEmpty()) {
			final List<SeatDto> seatDtos = new ArrayList<SeatDto>(seats.size());
			for (Seat seat : seats) {
				seatDtos.add(SeatTransformer.transform(seat));
			}
			if (seatDtos != null && !seatDtos.isEmpty()) {
				Collections.sort(seatDtos);
			}
			movieShowDto.setSeats(seatDtos);
		}
		return movieShowDto;
	}
	
	
}

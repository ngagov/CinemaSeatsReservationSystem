package com.ccbilleu.cinema.transform;

import com.ccbilleu.cinema.db.model.Theatre;
import com.ccbilleu.cinema.dto.TheatreDto;

public class TheatreTransformer {
	public static TheatreDto transform(Theatre theatre) {
		if (theatre == null) {
			return null;
		}
		final TheatreDto theatreDto = new TheatreDto();
		theatreDto.setId(theatre.getId());
		theatreDto.setName(theatre.getName());
		return theatreDto;
	}
}

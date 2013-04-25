package com.ccbilleu.cinema.controller;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccbilleu.cinema.db.exception.DaoException;
import com.ccbilleu.cinema.dto.MovieShowDto;
import com.ccbilleu.cinema.service.SeatService;

@Controller
@RequestMapping("/seat")
public class SeatController {
	@Autowired
	private SeatService seatService;
	
	private static final Logger log = Logger.getLogger(SeatController.class);
	
	@RequestMapping(value="/save", method = RequestMethod.POST, headers ={"Accept=application/json", "Content-Type=application/json"})
	public @ResponseBody MovieShowDto save(@RequestBody MovieShowDto movieShowDto, Principal principal) {
		log.debug("Received movieShowDto = "+movieShowDto);
		try {
			seatService.updateSeats(movieShowDto, principal);
			movieShowDto.setErrorMessage("You booked your seats with success. \nYou will have to pay for the places 15 minutes before the beginning of the movie.\nYour reservation code is 123456789");
		} catch (DaoException e) {
			log.error(e.getMessage(), e);
			movieShowDto.setErrorMessage("Error: " + e.getMessage());
		}
		return movieShowDto;
	}
}

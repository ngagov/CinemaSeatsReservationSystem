package com.ccbilleu.cinema.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccbilleu.cinema.db.exception.DaoException;
import com.ccbilleu.cinema.db.model.Cinema;
import com.ccbilleu.cinema.dto.CinemaDto;
import com.ccbilleu.cinema.service.CinemaService;

@Controller
@RequestMapping("/cinema")
public class CinemaController {

	@Autowired
	private CinemaService cinemaService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	@Transactional
	public @ResponseBody CinemaDto getCinema(Principal principal) {
		try {
			Cinema cinema = cinemaService.getDefaultCinema();
			CinemaDto cinemaDto = new CinemaDto();
			cinemaDto.setId(cinema.getId());
			cinemaDto.setName(cinema.getName());
			return cinemaDto;
		} catch (DaoException e) {
			CinemaDto cinemaDto = new CinemaDto();
			cinemaDto.setErrorMessage(e.getMessage());
			return cinemaDto;
		}
	}
}
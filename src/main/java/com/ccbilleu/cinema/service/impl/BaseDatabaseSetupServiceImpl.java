package com.ccbilleu.cinema.service.impl;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.ccbilleu.cinema.DateUtil;
import com.ccbilleu.cinema.db.exception.DaoException;
import com.ccbilleu.cinema.db.model.Cinema;
import com.ccbilleu.cinema.db.model.Movie;
import com.ccbilleu.cinema.db.model.MovieShow;
import com.ccbilleu.cinema.db.model.Seat;
import com.ccbilleu.cinema.db.model.SeatStatus;
import com.ccbilleu.cinema.db.model.Theatre;
import com.ccbilleu.cinema.service.CinemaService;
import com.ccbilleu.cinema.service.DatabaseSetupService;

public class BaseDatabaseSetupServiceImpl implements DatabaseSetupService {
	
	@PersistenceContext
	private EntityManager em;
	
	private CinemaService cinemaService;
	
	@Transactional
	public void setupTestData() throws DaoException {
		
		Cinema cinema = cinemaService.getDefaultCinema();
		if (cinema != null) {
			//database is already initialized
			return;
		}
		cinema = createCinema("Arena Cinema Max");
		
		final Theatre theatre1 = createTheatre(cinema, "Big Theatre");
		final Theatre theatre2 = createTheatre(cinema, "Small Theatre");
		
		final Movie movie1 = createMovie(cinema, "Resident Evil", "resources/images/resident_evil_extinction_ver3.jpg", "Alice fights alongside a resistance movement in the continuing battle against the Umbrella Corporation and the undead.", new Date());
		final Movie movie2 = createMovie(cinema, "Men in black", "resources/images/Men_in_Black_Poster.jpg", "Agent J travels in time to M.I.B.'s early days in 1969 to stop an alien from assassinating his friend Agent K and changing history.", new Date());
		final Movie movie3 = createMovie(cinema, "300", "resources/images/300.jpg", "King Leonidas and a force of 300 men fight the Persians at Thermopylae in 480 B.C.", new Date());
		
		final Movie movie4 = createMovie(cinema, "Riddick", "resources/images/113030.jpg", "The infamous Riddick has been left for dead on a sun-scorched planet that appears to be lifeless. Soon, however, he finds himself fighting for survival against alien predators more lethal than any human he’s encountered.", DateUtil.getFutureDate(5));
		final Movie movie5= createMovie(cinema, "The Iceman", "resources/images/84870.jpg", "Inspired by actual events, The Iceman follows notorious contract killer Richard Kuklinski (Michael Shannon) from his early days in the mob until his arrest for the murder of more than 100 men.", DateUtil.getFutureDate(15));
		
		final MovieShow movieShow1 = createMovieShow(movie1, theatre1, new Date());
		final MovieShow movieShow2 = createMovieShow(movie2, theatre1, new Date());
		final MovieShow movieShow3 = createMovieShow(movie2, theatre2, new Date());
		final MovieShow movieShow4 = createMovieShow(movie3, theatre1, new Date());
		final MovieShow movieShow5 = createMovieShow(movie3, theatre2, new Date());
		
		cinema.getMovies().add(movie1);
		cinema.getMovies().add(movie2);
		cinema.getMovies().add(movie3);
		cinema.getMovies().add(movie4);
		cinema.getMovies().add(movie5);
		
		final int numberOfSeatsBigTheatre = 30;
		createSeats(numberOfSeatsBigTheatre, new MovieShow[]{movieShow1, movieShow2, movieShow4});
		
		final int numberOfSeatsSmallTheatre = 10;
		createSeats(numberOfSeatsSmallTheatre, new MovieShow[]{movieShow3, movieShow5});

		em.flush();
	}
	
	@Required
	public void setCinemaService(CinemaService cinemaService) {
		this.cinemaService = cinemaService;
	}
	
	private void createSeats(int numberOfSeats, MovieShow[] movieShows) {
		for (MovieShow movieShow : movieShows) {
			createSeats(numberOfSeats, movieShow);
		}
	}
	
	private void createSeats(int numberOfSeats, MovieShow movieShow) {
		for (int i = 0; i < numberOfSeats; i++) {
			createSeat(i, movieShow);
		}
	}
	
	private Movie createMovie(Cinema cinema, String name, String imageUrl, String description, Date date) {
		final Movie movie = new Movie();
		movie.setCinema(cinema);
		movie.setName(name);
		movie.setImageUrl(imageUrl);
		movie.setDescription(description);
		movie.setReleaseDate(date);
		em.persist(movie);
		return movie;
	}
	
	private Cinema createCinema(String name) {
		final Cinema cinema = new Cinema();
		cinema.setName(name);
		em.persist(cinema);
		return cinema;
	}
	
	private Theatre createTheatre(Cinema cinema, String name) {
		final Theatre theatre = new Theatre();
		theatre.setCinema(cinema);
		theatre.setName(name);
		em.persist(theatre);
		return theatre;
	}
	
	private MovieShow createMovieShow(Movie movie, Theatre theatre, Date date) {
		final MovieShow movieShow = new MovieShow();
		movieShow.setMovie(movie);
		movieShow.setTheatre(theatre);
		movieShow.setShowDate(date);
		em.persist(movieShow);
		movie.getMovieShows().add(movieShow);
		theatre.getMovieShows().add(movieShow);
		return movieShow;
	}

	private Seat createSeat(Integer number, MovieShow movieShow) {
		final Seat seat = new Seat();
		seat.setNumber(number);
		if (number % 7 == 0) {
			seat.setSeatStatus(SeatStatus.OCCUPIED);
		}
		seat.setMovieShow(movieShow);
		em.persist(seat);
		movieShow.getSeats().add(seat);
		em.merge(movieShow);
		return seat;
	}
}

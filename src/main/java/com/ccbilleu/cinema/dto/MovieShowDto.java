package com.ccbilleu.cinema.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

public class MovieShowDto extends ErrorDto implements Comparable<MovieShowDto> {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String showDate;
	
	private TheatreDto theatreDto;
	
	private List<SeatDto> seats = new ArrayList<SeatDto>(0);;
	
	public List<SeatDto> getSeats() {
		return seats;
	}

	public void setSeats(List<SeatDto> seats) {
		this.seats = seats;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShowDate() {
		return showDate;
	}

	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}

	public TheatreDto getTheatreDto() {
		return theatreDto;
	}

	public void setTheatreDto(TheatreDto theatreDto) {
		this.theatreDto = theatreDto;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}

	public int compareTo(MovieShowDto o) {
		return id.compareTo(o.id);
	}
}

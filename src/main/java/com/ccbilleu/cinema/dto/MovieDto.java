package com.ccbilleu.cinema.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

public class MovieDto extends ErrorDto implements Comparable<MovieDto> {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private String imageUrl;
	
	private String description;
	
	private String releaseDate;
	
	private List<MovieShowDto> movieShows = new ArrayList<MovieShowDto>(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<MovieShowDto> getMovieShows() {
		return movieShows;
	}

	public void setMovieShows(List<MovieShowDto> movieShows) {
		this.movieShows = movieShows;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}
	
	public int compareTo(MovieDto other) {
		return name.compareTo(other.name);
	}
}

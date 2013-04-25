package com.ccbilleu.cinema.db.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class Theatre implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="THEATRE_ID")
	private Long id;
	
	@Column(unique=true)
	private String name;
	
	@ManyToOne
	private Cinema cinema;
	
	@OneToMany(mappedBy = "theatre")
    private Set<MovieShow> movieShows = new HashSet<MovieShow>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public Set<MovieShow> getMovieShows() {
		return movieShows;
	}

	public void setMovieShows(Set<MovieShow> movieShows) {
		this.movieShows = movieShows;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}

}

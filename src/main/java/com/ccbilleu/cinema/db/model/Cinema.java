package com.ccbilleu.cinema.db.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class Cinema implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CINEMA_ID")
	private Long id;
	
	@Column(unique=true)
	private String name;
	
	@OneToMany(mappedBy = "cinema")
	private Set<Movie> movies = new HashSet<Movie>();
	
	@OneToMany(mappedBy = "cinema")
	private Set<Theatre> theaters = new HashSet<Theatre>();
	
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
	
	public Set<Movie> getMovies() {
		return movies;
	}
	
	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}
	
	public Set<Theatre> getTheaters() {
		return theaters;
	}
	
	public void setTheaters(Set<Theatre> theaters) {
		this.theaters = theaters;
	}
	
	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}
}

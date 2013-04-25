package com.ccbilleu.cinema.dto;

import org.apache.commons.lang.builder.ToStringBuilder;

public class SeatDto extends ErrorDto implements Comparable<SeatDto> {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Integer number;
	
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}

	public int compareTo(SeatDto other) {
		return number.compareTo(other.number);
	}
}

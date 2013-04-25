package com.ccbilleu.cinema.dto;

import java.io.Serializable;

public class ErrorDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}

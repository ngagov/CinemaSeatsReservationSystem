package com.ccbilleu.cinema.db.exception;

public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;

	public DaoException() {
		super();
	}

	public DaoException(String errorMessage) {
		super(errorMessage);
	}
	
}

package com.ccbilleu.cinema.service;

import com.ccbilleu.cinema.db.exception.DaoException;

public interface DatabaseSetupService {
	void setupTestData() throws DaoException;
}

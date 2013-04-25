package com.ccbilleu.cinema.service.impl;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.ccbilleu.cinema.db.exception.DaoException;
import com.ccbilleu.cinema.service.DatabaseSetupService;
import com.ccbilleu.cinema.service.TestService;

@Transactional
public class BaseTestServiceImpl implements TestService {

	private DatabaseSetupService databaseSetupService;
	
	@Transactional
	public void init() throws DaoException {
		databaseSetupService.setupTestData();
	}

	@Required
	public void setDatabaseSetupService(DatabaseSetupService databaseSetupService) {
		this.databaseSetupService = databaseSetupService;
	}
	
	
}

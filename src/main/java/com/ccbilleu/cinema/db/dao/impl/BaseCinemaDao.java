package com.ccbilleu.cinema.db.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ccbilleu.cinema.db.dao.CinemaDao;
import com.ccbilleu.cinema.db.exception.DaoException;
import com.ccbilleu.cinema.db.model.Cinema;

public class BaseCinemaDao implements CinemaDao {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Cinema> getCinemas() throws DaoException {
		return em.createQuery("SELECT c FROM Cinema c ").getResultList();
	}
}

package be.vdab.dao;

import javax.persistence.EntityManager;

import be.vdab.filters.JPAFilter;

public abstract class AbstractDAO {

	public AbstractDAO() {
		super();
	}

	public EntityManager getEntityManager() {
		return JPAFilter.getEntityManager();
	}

	public void beginTransaction() {
		getEntityManager().getTransaction().begin();
	}

	public void commit() {
		getEntityManager().getTransaction().commit();
	}

	public void rollback() {
		getEntityManager().getTransaction().rollback();
	}

}
package com.med.firstapp.dao;

import java.io.Serializable;

import org.hibernate.SessionFactory;

public interface AbstractDao<PK extends Serializable, T> {

    //just for testing
	SessionFactory getSessionFactory();

	T findById(PK key);

	void save(T entity);
	
	void persist(T entity);

	void update(T entity);

	void saveOrUpdate(T entity);

	T merge(T entity);

	void delete(T entity);

}
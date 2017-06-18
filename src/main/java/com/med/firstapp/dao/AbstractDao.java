package com.med.firstapp.dao;

import java.io.Serializable;
import java.util.List;

public interface AbstractDao<PK extends Serializable, T> {

	void remove(T entity);

	T merge(T entity);

	void persist(T entity);

	T findById(PK key);

	List<T> findAll();



}
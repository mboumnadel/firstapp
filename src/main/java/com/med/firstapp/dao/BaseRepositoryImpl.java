package com.med.firstapp.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.transaction.annotation.Transactional;

public class BaseRepositoryImpl<T, ID extends Serializable> extends QueryDslJpaRepository<T, ID> {

	private final EntityManager em;

	public BaseRepositoryImpl(JpaEntityInformation<T,ID> entityInformation, EntityManager entityManager){
		super(entityInformation, entityManager);
		this.em = entityManager;
	}

	public BaseRepositoryImpl(JpaEntityInformation<T,ID> entityInformation, EntityManager entityManager, EntityPathResolver resolver){
		super(entityInformation, entityManager, resolver);
		this.em = entityManager;
	}

	@Transactional
	public void refresh(T t) {
		em.refresh(t);
	}
}
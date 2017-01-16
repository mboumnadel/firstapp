package com.med.firstapp.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDaoImpl<PK extends Serializable, T> implements AbstractDao<PK, T> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
	public SessionFactory getSessionFactory(){ 
    	return sessionFactory;
    }
    
    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDaoImpl(){
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }
 
    @Override
	public T findById(PK key) {
        return (T) getSession().get(persistentClass, key);
    }
 
    @Override
	public void persist(T entity) {
        getSession().persist(entity);
    }
 
    @Override
	public void save(T entity) {
        getSession().save(entity);
    }
    
    @Override
	public void update(T entity) {
        getSession().update(entity);
    }
    
    @Override
	public void saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
    }
    
    @SuppressWarnings("unchecked")
	@Override
	public T merge(T entity) {
        return (T) getSession().merge(entity);
    }
    
    @Override
	public void delete(T entity) {
        getSession().delete(entity);
    }
    
    protected Criteria createEntityCriteria(){
        return getSession().createCriteria(persistentClass);
    }
 
}


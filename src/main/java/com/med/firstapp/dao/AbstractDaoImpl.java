package com.med.firstapp.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class AbstractDaoImpl<PK extends Serializable, T> implements AbstractDao<PK, T> {

    //@Autowired
    //@PersistenceUnit
    //private EntityManagerFactory entityManagerFactory;
    
    @PersistenceContext
    private EntityManager entityManager;

    /*
    protected EntityManagerFactory getEntityManagerFactory(){ 
    	return entityManagerFactory;
    }
    */
    
    
    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDaoImpl(){
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    protected EntityManager getEntityManager(){
    	// Was sessionFactory.getCurrentSession();
    	//EntityManager entityManager = entityManagerFactory.createEntityManager();
    	System.out.println("AbstractDaoImpl getEntityManager hasCode: " + this.entityManager.hashCode());
    	System.out.println("AbstractDaoImpl getEntityManagerFactory hasCode: " + this.entityManager.getEntityManagerFactory().hashCode());

    	return this.entityManager;
    }
 
    @Override
	public T findById(PK key) {
        return (T) getEntityManager().find(persistentClass, key);
    }
 
    @Override
	public void persist(T entity) {
    	getEntityManager().persist(entity);
    }
    
	@Override
	public T merge(T entity) {
        return (T) getEntityManager().merge(entity);
    }
    
    @Override
	public void remove(T entity) {
    	getEntityManager().remove(entity);
    }
    
	@Override
	public List<T> findAll() {

		
		System.out.println("AbstractDaoImpl findAll start ");
		
        CriteriaBuilder builder = getCriteriaBuilder();
        
        CriteriaQuery<T> criteria = builder.createQuery( persistentClass );
        Root<T> root = criteria.from( persistentClass );
        criteria.select( root );

        
        System.out.println("AbstractDaoImpl findAll middle");
        
        List<T> list= getEntityManager().createQuery( criteria ).getResultList();
        
        System.out.println("AbstractDaoImpl findAll End");
        
        return list;
	}
    
    
	protected CriteriaBuilder getCriteriaBuilder(){

    	CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
    	return builder;
    }
 
}


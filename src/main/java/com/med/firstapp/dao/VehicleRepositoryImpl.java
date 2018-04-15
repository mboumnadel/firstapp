package com.med.firstapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import com.med.firstapp.model.QVehicle;
import com.med.firstapp.model.Vehicle;
import com.med.firstapp.model.Vehicle_;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;


// repository-impl-postfix
// Just add the postfix Impl and Spring will pick up this custom implementation

public class VehicleRepositoryImpl implements VehicleRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void refresh(Vehicle vehicle) {
		em.refresh(vehicle);
	}

	@Override
	public Vehicle findByIdUsingJpa(Integer id){

		TypedQuery<Vehicle> query = em.createQuery("SELECT v FROM Vehicle v WHERE v.id = :id", Vehicle.class);
		query.setParameter("id", id);
		Vehicle result = query.getSingleResult();

		return result;
	}

	@Override
	public Vehicle findByIdUsingJpaCriteria(Integer id){

		CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Vehicle> criteria = builder.createQuery( Vehicle.class );
        Root<Vehicle> root = criteria.from( Vehicle.class );

        criteria.select( root );
        criteria.where( builder.equal( root.get(Vehicle_.id), id ) );
        //criteria.where( builder.equal( root.get("id"), id ) );

        Vehicle Vehicle = em.createQuery( criteria ).getSingleResult();
        return Vehicle;
	}

	@Override
	public Vehicle findByIdUsingJpaQuerydsl(Integer id){

		//JPAQuery<Vehicle> query = new JPAQuery<Vehicle>(em);
		JPAQueryFactory f = new JPAQueryFactory(em);

		QVehicle vehicle = QVehicle.vehicle;

		JPAQuery<Vehicle> query = f.selectFrom(vehicle);
		Vehicle v = query.from(vehicle)
				.where(vehicle.id.eq(id))
				.fetchOne();

		return v;
	}
}

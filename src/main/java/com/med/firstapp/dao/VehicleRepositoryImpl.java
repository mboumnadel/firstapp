package com.med.firstapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.med.firstapp.model.Vehicle;


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

}

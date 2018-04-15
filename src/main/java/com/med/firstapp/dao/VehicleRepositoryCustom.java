package com.med.firstapp.dao;

import com.med.firstapp.model.Vehicle;

public interface VehicleRepositoryCustom {

	public void refresh(Vehicle vehicle);

	public Vehicle findByIdUsingJpa(Integer id);
	public Vehicle findByIdUsingJpaCriteria(Integer id);

	public Vehicle findByIdUsingJpaQuerydsl(Integer id);

}

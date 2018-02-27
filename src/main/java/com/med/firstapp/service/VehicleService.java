package com.med.firstapp.service;

import java.util.List;

import com.med.firstapp.model.Vehicle;

public interface VehicleService {

	Vehicle findById(int id);

	void persist(Vehicle vehicle);

	Vehicle merge(Vehicle vehicle);

	void remove(Vehicle vehicle);

	List<Vehicle> findAll();

	void testTransLoadSameEntityTwice();

	void testNoTransLoadSameEntityTwice();
}

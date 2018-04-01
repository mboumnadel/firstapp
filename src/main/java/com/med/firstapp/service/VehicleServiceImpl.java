package com.med.firstapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.med.firstapp.dao.VehicleDao;
import com.med.firstapp.model.Vehicle;



@Service("vehicleService")
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleDao dao;


	@Override
	@Transactional
	public Vehicle findById(int id) {
		return dao.findById(id);
	}


	@Override
	@Transactional
	public void persist(Vehicle vehicle) {
		dao.persist(vehicle);

	}

	@Override
	@Transactional
	public Vehicle merge(Vehicle vehicle) {
		System.out.println("===== just before calling dao.merge ");

		Vehicle vehicle2 = dao.merge(vehicle);

		System.out.println("===== just after calling dao.merge ");


		return vehicle2;
	}


	@Override
	@Transactional
	public void remove(Vehicle vehicle) {
		dao.remove(vehicle);
	}


	@Override
	@Transactional
	public List<Vehicle> findAll() {
		return dao.findAll();
	}

	@Override
	public void testNoTransLoadSameEntityTwice(){

		//First Level Cache is NOT working if no transaction is started
		//Two SQL queries are issued to DB
		//Same entityManager

		System.out.println("****** starting testNoTransLoadSameEntityTwice VehicleService");

		Vehicle vehicle1 = dao.findById(1);
		System.out.println("****** between testNoTransLoadSameEntityTwice VehicleService");
		Vehicle vehicle2 = dao.findById(1);

		System.out.println("****** ending testNoTransLoadSameEntityTwice VehicleService");
	}

	@Override
	@Transactional
	public void testTransLoadSameEntityTwice(){

		//First Level Cache is working and caching entity
		//One SQL query is issued to DB
		//Same entityManager

		System.out.println("****** starting testTransLoadSameEntityTwice VehicleService");

		Vehicle vehicle1 = dao.findById(1);
		System.out.println("****** between testTransLoadSameEntityTwice VehicleService");
		Vehicle vehicle2 = dao.findById(1);

		System.out.println("****** ending testTransLoadSameEntityTwice VehicleService");
	}
}
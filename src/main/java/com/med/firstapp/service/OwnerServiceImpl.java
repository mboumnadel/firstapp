package com.med.firstapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.med.firstapp.dao.OwnerDao;
import com.med.firstapp.dao.VehicleDao;
import com.med.firstapp.model.Owner;
import com.med.firstapp.model.Vehicle;



@Service("ownerService")
public class OwnerServiceImpl implements OwnerService {

	@Autowired
	private OwnerDao dao;

	@Autowired
	private VehicleDao vehicledao;


	@Override
	public Owner findById(int id) {
		return dao.findById(id);
	}


	@Override
	public void persist(Owner owner) {
		dao.persist(owner);

	}

	@Override
	public Owner merge(Owner owner) {
		return dao.merge(owner);
	}


	@Override
	public void remove(Owner owner) {
		dao.remove(owner);
	}


	@Override
	public List<Owner> findAll() {
		return dao.findAll();
	}

	@Override
	public void testNoTransLoadSameEntityTwice(){

		//First Level Cache is NOT working if no transaction is started
		//Two SQL queries are issued to DB
		//Same entityManager

		System.out.println("****** starting testNoTransLoadSameEntityTwice OwnerService");

		Owner owner1 = dao.findById(1);
		System.out.println("****** between testNoTransLoadSameEntityTwice OwnerService");
		Owner owner2 = dao.findById(1);

		System.out.println("****** ending testNoTransLoadSameEntityTwice OwnerService");
	}

	@Override
	@Transactional
	public void testTransLoadSameEntityTwice(){

		//First Level Cache is working and caching entity
		//One SQL query is issued to DB
		//Same entityManager

		System.out.println("****** starting testTransLoadSameEntityTwice OwnerService");

		Owner owner1 = dao.findById(1);
		System.out.println("****** between testTransLoadSameEntityTwice OwnerService");
		Owner owner2 = dao.findById(1);

		System.out.println("****** ending testTransLoadSameEntityTwice OwnerService");
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void testTransTwoDaos() throws Exception {

		//First Level Cache is working and caching entities for both DAOs
		//One SQL query is issued to DB for each DAO
		//Every DAO has its own Same entityManager instance
		//Same transaction spans over the two DAOs

		System.out.println("****** starting testTransTwoDaos OwnerService");

		Owner owner1 = dao.findById(1);
		Vehicle vehicle1 = vehicledao.findById(1);

		owner1.setName(owner1.getName() + " ABC ");
		vehicle1.setModel(vehicle1.getModel() + " XYZ ");

		System.out.println("****** between testTransTwoDaos OwnerService");
		Owner owner2 = dao.findById(1);
		Vehicle vehicle2 = vehicledao.findById(1);

		System.out.println("****** ending testTransTwoDaos OwnerService");

		//throw new Exception("Forced Exception ---------- ");
	}
}
package com.med.firstapp.service;

import java.util.List;

import com.med.firstapp.model.Owner;

public interface OwnerService {

	Owner findById(int id);

	void persist(Owner owner);

	Owner merge(Owner owner);

	void remove(Owner owner);

	List<Owner> findAll();

	void testTransLoadSameEntityTwice();

	void testNoTransLoadSameEntityTwice();

	void testTransTwoDaos() throws Exception;
}

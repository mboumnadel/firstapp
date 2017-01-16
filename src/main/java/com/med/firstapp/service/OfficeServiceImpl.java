package com.med.firstapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.med.firstapp.dao.OfficeDao;
import com.med.firstapp.model.Office;

@Service("officeService")
@Transactional
public class OfficeServiceImpl implements OfficeService {

	@Autowired
    private OfficeDao dao;
	
	@Override
	public Office findOfficeById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<Office> findAllOffices() {
		return dao.findAll();	
	}

}

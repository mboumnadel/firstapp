package com.med.firstapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.med.firstapp.dao.CustomerDao;
import com.med.firstapp.model.Customer;


@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao dao;
	
	
	@Override
	public Customer findById(int id) {
		return dao.findById(id);
	}

	
	@Override
	public void persist(Customer customer) {
		dao.persist(customer);
		
	}

	@Override
	public Customer merge(Customer customer) {
		return dao.merge(customer);
	}

	
	@Override
	public void remove(Customer customer) {
		dao.remove(customer);
	}
	
	
	@Override
	public List<Customer> findAll() {
		return dao.findAll();
	}
}
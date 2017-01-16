package com.med.firstapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.med.firstapp.dao.CustomerDao;
import com.med.firstapp.model.Customer;


@Service("customerService")
//@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao dao;
	
	@Transactional
	@Override
	public Customer findCustomerById(int id) {
		return dao.findById(id);
	}

	@Transactional
	@Override
	public void persistCustomer(Customer customer) {
		dao.persist(customer);
		
	}
	
	@Transactional
	@Override
	public void saveCustomer(Customer customer) {
		 dao.save(customer);
	}

	@Transactional
	@Override
	public void updateCustomer(Customer customer) {
		dao.update(customer);
		
	}

	@Transactional
	@Override
	public void saveOrUpdateCustomer(Customer customer) {
		dao.saveOrUpdate(customer);
		
	}

	@Transactional
	@Override
	public Customer mergeCustomer(Customer customer) {
		return dao.merge(customer);
	}

	@Transactional
	@Override
	public void deleteCustomer(Customer customer) {
		dao.delete(customer);
	}
	
	@Transactional
	@Override
	public List<Customer> findAllCustomers() {
		return dao.findAll();
	}
}
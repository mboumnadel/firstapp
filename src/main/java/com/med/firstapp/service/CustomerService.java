package com.med.firstapp.service;

import java.util.List;

import com.med.firstapp.model.Customer;

public interface CustomerService {

	Customer findById(int id);
    
    void persist(Customer Customer);

	Customer merge(Customer Customer);
	
	void remove(Customer Customer);
	
	List<Customer> findAll(); 
}

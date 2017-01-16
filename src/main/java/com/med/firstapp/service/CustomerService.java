package com.med.firstapp.service;

import java.util.List;

import com.med.firstapp.model.Customer;

public interface CustomerService {

	Customer findCustomerById(int id);
    
    void persistCustomer(Customer Customer);

	void saveCustomer(Customer Customer);

	void updateCustomer(Customer Customer);
	
	void saveOrUpdateCustomer(Customer Customer);

	Customer mergeCustomer(Customer Customer);
	
	void deleteCustomer(Customer Customer);
	
	List<Customer> findAllCustomers(); 
}

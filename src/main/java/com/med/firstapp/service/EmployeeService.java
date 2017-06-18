package com.med.firstapp.service;

import java.util.List;

import com.med.firstapp.model.Employee;

public interface EmployeeService {

	Employee findById(int id);
    
    void persist(Employee employee);

	Employee merge(Employee employee);
	
	void remove(Employee employee);

    void deleteByNumber(String number);

    List<Employee> findAll(); 

    Employee findByNumber(String number);
 
    boolean isEmployeeNumberUnique(Integer id, String number);
}

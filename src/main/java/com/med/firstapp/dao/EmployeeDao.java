package com.med.firstapp.dao;

import java.util.List;

import com.med.firstapp.model.Employee;

public interface EmployeeDao extends AbstractDao<Integer, Employee> {

//    Employee findById(int id);
//    
//    void persistEmployee(Employee employee);
//
//	void saveEmployee(Employee employee);
//
//	void updateEmployee(Employee employee);
//	
//	void saveOrUpdateEmployee(Employee employee);
//
//	Employee mergeEmployee(Employee employee);
//	
//	void deleteEmployee(Employee employee);
	
	
	
    
    void deleteByNumber(String number);
     
    List<Employee> findAll();
 
    Employee findByNumber(String number);

}

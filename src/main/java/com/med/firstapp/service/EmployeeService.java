package com.med.firstapp.service;

import java.util.List;

import com.med.firstapp.model.Employee;

public interface EmployeeService {

	Employee findEmployeeById(int id);
    
    void persistEmployee(Employee employee);

	void saveEmployee(Employee employee);

	void updateEmployee(Employee employee);
	
	void saveOrUpdateEmployee(Employee employee);

	Employee mergeEmployee(Employee employee);
	
	void deleteEmployee(Employee employee);

    void deleteEmployeeByNumber(String number);

    List<Employee> findAllEmployees(); 

    Employee findEmployeeByNumber(String number);
 
    boolean isEmployeeNumberUnique(Integer id, String number);

    public void main(Employee employee);
}

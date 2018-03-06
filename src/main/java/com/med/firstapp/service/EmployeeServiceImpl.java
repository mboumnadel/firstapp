package com.med.firstapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.med.firstapp.dao.EmployeeDao;
import com.med.firstapp.model.Employee;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
    private EmployeeDao dao;


	@Override
	public Employee findById(int id) {
		return dao.findById(id);
	}


	@Override
	public void persist(Employee employee) {
		dao.persist(employee);

	}

	@Override
	public Employee merge(Employee employee) {
		return dao.merge(employee);
	}


	@Override
	public void remove(Employee employee) {
		dao.remove(employee);
	}

	@Override
	public void deleteByNumber(String number) {
		dao.deleteByNumber(number);
	}


	@Override
	public List<Employee> findAll() {
		return dao.findAll();
	}


	@Override
	public Employee findByNumber(String number) {
		return dao.findByNumber(number);
	}

	@Override
	public boolean isEmployeeNumberUnique(Integer id, String number) {
		 Employee employee = findByNumber(number);
	     return ( employee == null || ((id != null) && (employee.getId() == id)));
	}
}

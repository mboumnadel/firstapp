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
		
		/*System.out.println("** findById 10 1 **");
		dao.findById(10);
		
		System.out.println("** findById 10 2 **");
		
		dao.findById(10);
		System.out.println("** findById 10 3 **");*/
		
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

	//@Override
	public void updateEmployee_old(Employee employee) {

		 /*
	     * Since the method is running with Transaction, No need to call hibernate update explicitly.
	     * Just fetch the entity from db and update it with proper values within transaction.
	     * It will be updated in db once transaction ends. 
	     */
		
		Employee entity = dao.findById(employee.getId());
        if(entity!=null){
            entity.setNumber(employee.getNumber());
            entity.setFirstName(employee.getFirstName());
            entity.setLastName(employee.getLastName());
            
            entity.setExtension(employee.getExtension());
            entity.setEmail(employee.getEmail());
            //entity.setOfficeCode(employee.getOfficeCode());
            //entity.setOfficeCode(1);
            //entity.setReportTo(employee.getReportTo());
            //entity.setReportsTo(1);
            entity.setJobTitle(employee.getJobTitle());

        }

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

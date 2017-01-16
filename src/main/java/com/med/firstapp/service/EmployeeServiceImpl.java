package com.med.firstapp.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.med.firstapp.dao.EmployeeDao;
import com.med.firstapp.model.Employee;

@Service("employeeService")
//@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
    private EmployeeDao dao;
	
	@Transactional
	@Override
	public Employee findEmployeeById(int id) {
		return dao.findById(id);
	}

	@Transactional
	@Override
	public void persistEmployee(Employee employee) {
		dao.persist(employee);
		
	}
	
	@Transactional
	@Override
	public void saveEmployee(Employee employee) {
		 dao.save(employee);
	}

	@Transactional
	@Override
	public void updateEmployee(Employee employee) {
		dao.update(employee);
		
	}

	@Transactional
	@Override
	public void saveOrUpdateEmployee(Employee employee) {
		dao.saveOrUpdate(employee);
		
	}

	@Transactional
	@Override
	public Employee mergeEmployee(Employee employee) {
		return dao.merge(employee);
	}

	@Transactional
	@Override
	public void deleteEmployee(Employee employee) {
		dao.delete(employee);
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

	@Transactional
	@Override
	public void deleteEmployeeByNumber(String number) {
		dao.deleteByNumber(number);
	}

	@Transactional
	@Override
	public List<Employee> findAllEmployees() {
		return dao.findAll();
	}

	@Transactional
	@Override
	public Employee findEmployeeByNumber(String number) {
		return dao.findByNumber(number);
	}

	@Override
	public boolean isEmployeeNumberUnique(Integer id, String number) {
		 Employee employee = findEmployeeByNumber(number);
	     return ( employee == null || ((id != null) && (employee.getId() == id)));
	}

	@Override
	public void main(Employee employee){
		
		SessionFactory sessionFactory = dao.getSessionFactory();

		Session sess = sessionFactory.openSession();
		
		//do some work
//		sess.update(employee);
//		
//		sess.close();
		
		
		 Transaction tx = null;
		 try {
		     tx = sess.beginTransaction();
		     //do some work
		     
		     
		     sess.update(employee);     
		     
		     tx.commit();
		 }
		 catch (Exception e) {
		     if (tx!=null) tx.rollback();
		     throw e;
		 }
		 finally {
		     sess.close();
		 }
		 
		
	}
}

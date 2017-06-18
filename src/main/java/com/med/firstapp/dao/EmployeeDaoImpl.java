package com.med.firstapp.dao;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.med.firstapp.model.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDaoImpl<Integer, Employee> implements EmployeeDao {

	@Override
	public void deleteByNumber(String number) {

        Query query = getEntityManager().createQuery("select e from Employee e where p.ssn = :ssn" );
        query.setParameter( "name", number );
        query.executeUpdate();
	}

	@Override
	public Employee findByNumber(String number) {
		
		CriteriaBuilder builder = getCriteriaBuilder();
		
        CriteriaQuery<Employee> criteria = builder.createQuery( Employee.class );
        Root<Employee> root = criteria.from( Employee.class );
        criteria.select( root );
        criteria.where( builder.equal( root.get("number"), number ) );

        Employee employee = getEntityManager().createQuery( criteria ).getSingleResult();
        return employee;
        
	}

}

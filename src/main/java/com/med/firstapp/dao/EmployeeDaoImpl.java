package com.med.firstapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.med.firstapp.model.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDaoImpl<Integer, Employee> implements EmployeeDao {

	Session session = null;
	
	
	@Override
	public void deleteByNumber(String number) {
		org.hibernate.query.Query createQuery = getSession().createQuery("");
		
		Query query = getSession().createSQLQuery("delete from Employee where ssn = :number");
        query.setString("number", number);
        query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findAll() {
		Criteria criteria = createEntityCriteria();
        return (List<Employee>) criteria.list();
	}

	@Override
	public Employee findByNumber(String number) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("number", number));
        return (Employee) criteria.uniqueResult();
	}

}

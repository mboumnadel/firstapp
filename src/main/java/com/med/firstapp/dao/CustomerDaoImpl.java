package com.med.firstapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.med.firstapp.model.Customer;

@Repository("customerDao")
public class CustomerDaoImpl extends AbstractDaoImpl<Integer, Customer> implements CustomerDao { 

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> findAll() {
		Criteria criteria = createEntityCriteria();
        return (List<Customer>) criteria.list();
	}

}
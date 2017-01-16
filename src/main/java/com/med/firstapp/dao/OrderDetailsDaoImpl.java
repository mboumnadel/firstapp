package com.med.firstapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.med.firstapp.model.OrderDetails;

@Repository("orderDetailsDao")
public class OrderDetailsDaoImpl extends AbstractDaoImpl<Integer, OrderDetails> implements OrderDetailsDao { 

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDetails> findAll() {
		Criteria criteria = createEntityCriteria();
        return (List<OrderDetails>) criteria.list();
	}

}
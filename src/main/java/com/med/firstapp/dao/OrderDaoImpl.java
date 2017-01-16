package com.med.firstapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.med.firstapp.model.Order;


@Repository("orderDao")
public class OrderDaoImpl extends AbstractDaoImpl<Integer, Order> implements OrderDao { 

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findAll() {
		Criteria criteria = createEntityCriteria();
        return (List<Order>) criteria.list();
	}

	@Override
	public List<Order> getOrdersByCustomerId(Integer customerId) {
		Query<Order> query = getSession().createQuery("From Order WHERE customer.id = :customerId", Order.class);
		query.setParameter("customerId", customerId);
		List<Order> resultList = query.getResultList();

		return resultList;
	}
}
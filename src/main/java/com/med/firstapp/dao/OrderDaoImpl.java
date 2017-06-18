package com.med.firstapp.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.med.firstapp.model.Order;


@Repository("orderDao")
public class OrderDaoImpl extends AbstractDaoImpl<Integer, Order> implements OrderDao { 

	@Override
	public List<Order> getOrdersByCustomerId(Integer customerId) {

		TypedQuery<Order> query = getEntityManager().createQuery("SELECT o From Order o INNER JOIN FETCH o.customer c WHERE c.id = :customerId", Order.class);
		query.setParameter("customerId", customerId);

		List<Order> resultList = query.getResultList();

		return resultList;
	}

	@Override
	public Order findOrderAndDetailsById(int orderId) {
		
		TypedQuery<Order> query = getEntityManager().createQuery("SELECT o From Order o INNER JOIN FETCH o.orderDetails d WHERE o.id = :orderId", Order.class);
		query.setParameter("orderId", orderId);

		Order result = query.getSingleResult();

		return result;
	}
	
}
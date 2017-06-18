package com.med.firstapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.med.firstapp.dao.OrderDao;
import com.med.firstapp.model.Order;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
    private OrderDao dao;
	
	
	@Override
	public Order findById(int id) {
		return dao.findById(id);
	}

	
	@Override
	public void persist(Order order) {
		dao.persist(order);
		
	}

	@Override
	public Order merge(Order order) {
		return dao.merge(order);
	}

	
	@Override
	public void remove(Order order) {
		dao.remove(order);
	}
	
	
	@Override
	public List<Order> getOrdersByCustomerId(Integer customerId) {
		return dao.getOrdersByCustomerId(customerId);
	}
	
	@Override
	
	public Order findOrderAndDetailsById(int orderId) {
		
		Order order = dao.findOrderAndDetailsById(orderId);
		
//		Order order = dao.findById(orderId);
//		List<OrderDetails> orderDetailsList = order.getOrderDetails();
//	
//		for (OrderDetails orderDetails : orderDetailsList) {
//
//			Integer quantityOrdered = orderDetails.getQuantityOrdered();
//			Integer id = orderDetails.getProduct().getId();
//		}
		
		return order;
	}
}

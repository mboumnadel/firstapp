package com.med.firstapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.med.firstapp.dao.OrderDao;
import com.med.firstapp.model.Order;
import com.med.firstapp.model.OrderDetails;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
    private OrderDao dao;
	
	@Transactional
	@Override
	public Order findOrderById(int id) {
		return dao.findById(id);
	}

	@Transactional
	@Override
	public void persistOrder(Order order) {
		dao.persist(order);
		
	}
	
	@Transactional
	@Override
	public void saveOrder(Order order) {
		 dao.save(order);
	}

	@Transactional
	@Override
	public void updateOrder(Order order) {
		dao.update(order);
		
	}

	@Transactional
	@Override
	public void saveOrUpdateOrder(Order order) {
		dao.saveOrUpdate(order);
		
	}

	@Transactional
	@Override
	public Order mergeOrder(Order order) {
		return dao.merge(order);
	}

	@Transactional
	@Override
	public void deleteOrder(Order order) {
		dao.delete(order);
	}
	
	
	@Override
	public List<Order> getOrdersByCustomerId(Integer customerId) {
		return dao.getOrdersByCustomerId(customerId);
	}
	
	@Override
	@Transactional
	public Order findOrderAndDetailsById(int orderId) {
		Order order = dao.findById(orderId);
		List<OrderDetails> orderDetailsList = order.getOrderDetails();
	
		for (OrderDetails orderDetails : orderDetailsList) {
			
			System.out.println("Quantity Ordered:" + orderDetails.getQuantityOrdered());

			System.out.println("  product.id:" + orderDetails.getProduct().getId());
		}
		
		return order;
	}
}

package com.med.firstapp.service;

import java.util.List;

import com.med.firstapp.model.Order;

public interface OrderService {

	Order findOrderById(int id);
    
    void persistOrder(Order order);

	void saveOrder(Order order);

	void updateOrder(Order order);
	
	void saveOrUpdateOrder(Order order);

	Order mergeOrder(Order order);
	
	void deleteOrder(Order order);
	
	List<Order> getOrdersByCustomerId(Integer customerId);

	Order findOrderAndDetailsById(int orderId);

}

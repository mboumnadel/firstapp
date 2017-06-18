package com.med.firstapp.service;

import java.util.List;

import com.med.firstapp.model.Order;

public interface OrderService {

	Order findById(int id);
    
    void persist(Order order);

	Order merge(Order order);

	void remove(Order order) ;
	
	List<Order> getOrdersByCustomerId(Integer customerId);

	Order findOrderAndDetailsById(int orderId);

}

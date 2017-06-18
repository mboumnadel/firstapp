package com.med.firstapp.dao;

import java.util.List;

import com.med.firstapp.model.Order;

public interface OrderDao extends AbstractDao<Integer, Order> {

	List<Order> getOrdersByCustomerId(Integer customerId);

	Order findOrderAndDetailsById(int orderId);

}
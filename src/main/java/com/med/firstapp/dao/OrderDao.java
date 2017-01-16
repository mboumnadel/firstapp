package com.med.firstapp.dao;

import java.util.List;

import org.hibernate.query.Query;

import com.med.firstapp.model.Order;

public interface OrderDao extends AbstractDao<Integer, Order> {

    List<Order> findAll();

	List<Order> getOrdersByCustomerId(Integer customerId);

}
package com.med.firstapp.dao;

import org.springframework.stereotype.Repository;

import com.med.firstapp.model.OrderDetails;

@Repository("orderDetailsDao")
public class OrderDetailsDaoImpl extends AbstractDaoImpl<Integer, OrderDetails> implements OrderDetailsDao { 

}
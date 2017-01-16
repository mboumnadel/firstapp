package com.med.firstapp.dao;

import java.util.List;

import com.med.firstapp.model.OrderDetails;


public interface OrderDetailsDao extends AbstractDao<Integer, OrderDetails> {

    List<OrderDetails> findAll();

}
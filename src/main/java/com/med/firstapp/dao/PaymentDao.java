package com.med.firstapp.dao;

import java.util.List;

import com.med.firstapp.model.Payment;


public interface PaymentDao extends AbstractDao<Integer, Payment> {

    List<Payment> findAll();

}
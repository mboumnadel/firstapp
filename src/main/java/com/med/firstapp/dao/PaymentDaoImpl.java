package com.med.firstapp.dao;

import org.springframework.stereotype.Repository;

import com.med.firstapp.model.Payment;

@Repository("paymentDao")
public class PaymentDaoImpl extends AbstractDaoImpl<Integer, Payment> implements PaymentDao { 


}
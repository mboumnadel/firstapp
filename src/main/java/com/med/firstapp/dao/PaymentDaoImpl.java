package com.med.firstapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.med.firstapp.model.Payment;

@Repository("paymentDao")
public class PaymentDaoImpl extends AbstractDaoImpl<Integer, Payment> implements PaymentDao { 

	@SuppressWarnings("unchecked")
	@Override
	public List<Payment> findAll() {
		Criteria criteria = createEntityCriteria();
        return (List<Payment>) criteria.list();
	}

}
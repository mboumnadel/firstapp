package com.med.firstapp.dao;

import org.springframework.stereotype.Repository;

import com.med.firstapp.model.Customer;

@Repository("customerDao")
public class CustomerDaoImpl extends AbstractDaoImpl<Integer, Customer> implements CustomerDao {

}
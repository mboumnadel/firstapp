package com.med.firstapp.dao;

import java.util.List;

import com.med.firstapp.model.Customer;

public interface CustomerDao extends AbstractDao<Integer, Customer> {

    List<Customer> findAll();

}
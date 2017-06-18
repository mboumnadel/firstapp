package com.med.firstapp.dao;

import org.springframework.stereotype.Repository;

import com.med.firstapp.model.Product;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDaoImpl<Integer, Product> implements ProductDao { 

}

package com.med.firstapp.service;

import java.util.List;

import com.med.firstapp.model.Product;

public interface ProductService {

	Product findById(int id);

	List<Product> findAll();
	
}

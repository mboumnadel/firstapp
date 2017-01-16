package com.med.firstapp.service;

import java.util.List;

import com.med.firstapp.model.Product;

public interface ProductService {

	Product findProductById(int id);

	List<Product> findAllProducts();
	
}

package com.med.firstapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.med.firstapp.dao.ProductDao;
import com.med.firstapp.model.Product;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;
	
	@Override
	public Product findProductById(int id) {
		return productDao.findById(id);
	}

	@Override
	public List<Product> findAllProducts() {
		return productDao.findAll();
	}

}

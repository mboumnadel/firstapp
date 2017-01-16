package com.med.firstapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.med.firstapp.model.Product;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDaoImpl<Integer, Product> implements ProductDao { 

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAll() {
		Criteria criteria = createEntityCriteria();
        return (List<Product>) criteria.list();
	}

}

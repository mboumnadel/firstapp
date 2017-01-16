package com.med.firstapp.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.med.firstapp.model.Product;
import com.med.firstapp.model.ProductLine;

@Repository("productLineDao")
public class ProductLineDaoImpl extends AbstractDaoImpl<Integer, ProductLine> implements ProductLineDao {

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

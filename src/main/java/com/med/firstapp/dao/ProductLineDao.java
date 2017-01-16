package com.med.firstapp.dao;

import java.util.List;

import com.med.firstapp.model.Product;
import com.med.firstapp.model.ProductLine;


public interface ProductLineDao extends AbstractDao<Integer, ProductLine> {

    List<Product> findAll();

}


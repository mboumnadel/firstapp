package com.med.firstapp.dao;

import java.util.List;

import com.med.firstapp.model.Product;

public interface ProductDao extends AbstractDao<Integer, Product> {

    List<Product> findAll();

}

package com.med.firstapp.dao;

import org.springframework.stereotype.Repository;

import com.med.firstapp.model.ProductLine;

@Repository("productLineDao")
public class ProductLineDaoImpl extends AbstractDaoImpl<Integer, ProductLine> implements ProductLineDao {

}

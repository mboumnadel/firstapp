package com.med.firstapp.dao;

import java.util.List;

import com.med.firstapp.model.Office;

public interface OfficeDao extends AbstractDao<Integer, Office> {

    List<Office> findAll();

}

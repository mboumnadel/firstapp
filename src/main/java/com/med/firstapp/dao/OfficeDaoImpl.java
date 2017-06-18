package com.med.firstapp.dao;

import org.springframework.stereotype.Repository;

import com.med.firstapp.model.Office;

@Repository("officeDao")
public class OfficeDaoImpl extends AbstractDaoImpl<Integer, Office> implements OfficeDao {

}

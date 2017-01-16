package com.med.firstapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.med.firstapp.model.Office;

@Repository("officeDao")
public class OfficeDaoImpl extends AbstractDaoImpl<Integer, Office> implements OfficeDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Office> findAll() {
		Criteria criteria = createEntityCriteria();
        return (List<Office>) criteria.list();
	}

}

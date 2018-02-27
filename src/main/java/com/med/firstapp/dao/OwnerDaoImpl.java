package com.med.firstapp.dao;

import org.springframework.stereotype.Repository;

import com.med.firstapp.model.Owner;


@Repository("ownerDao")
public class OwnerDaoImpl extends AbstractDaoImpl<Integer, Owner> implements OwnerDao {

}
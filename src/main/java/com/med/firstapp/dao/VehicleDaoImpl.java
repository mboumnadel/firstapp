package com.med.firstapp.dao;

import org.springframework.stereotype.Repository;

import com.med.firstapp.model.Vehicle;


@Repository("vehicleDao")
public class VehicleDaoImpl extends AbstractDaoImpl<Integer, Vehicle> implements VehicleDao {

}
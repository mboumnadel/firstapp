package com.med.firstapp.dao;

import com.med.firstapp.model.QVehicle;
import com.querydsl.core.types.Predicate;

//class and methods should be package access level, not public

public final class VehiclePredicates {

	private VehiclePredicates() {}

    static public Predicate hasMake(String make) {
    	return QVehicle.vehicle.make.eq(make);
    }

    static public Predicate hasModel(String model) {
    	return QVehicle.vehicle.model.eq(model);
    }

    static public Predicate makeIsAndModelIsNot(String make, String model) {

    	return QVehicle.vehicle.make.eq(make).and(QVehicle.vehicle.model.eq(model).not());
    }
}

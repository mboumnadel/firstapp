package com.med.firstapp.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.med.firstapp.model.Vehicle;
import com.med.firstapp.model.Vehicle_;

// Creating Database Queries With the JPA Criteria API

//class and methods should be package access level, not public

public final class VehicleSpecifications {

	private VehicleSpecifications() {}

    static public Specification<Vehicle> hasModel(String model) {
        return new Specification<Vehicle>() {
            @Override
            public Predicate toPredicate(Root<Vehicle> root, CriteriaQuery<?> q, CriteriaBuilder cb) {
            	return cb.equal(root.get(Vehicle_.model), model);
            	//return cb.equal(root.get("model"), model);
            }
        };
    }

	static public Specification<Vehicle> hasMake(String make) {
		return (root, query, cb) -> {
			return cb.equal(root.get(Vehicle_.make), make);
			//return cb.equal(root.get("make"), make);
		};
	}

	static public Specification<Vehicle> makeIsAndModelIsNot(String make, String model) {

		return Specifications.where(hasMake(make)).and(
					Specifications.not(hasModel(model))
					);
	}
}

//Instead of accessing entity member directly like in root.get("male"), we can make use of JPA static metamodel classes.
//besides refactoring assistance it also helps in creating type-safe criteria queries.
//SingularAttribute // JPAMetaModelEntityProcessor

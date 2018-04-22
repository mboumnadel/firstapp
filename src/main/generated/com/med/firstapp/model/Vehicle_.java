package com.med.firstapp.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Vehicle.class)
public abstract class Vehicle_ {

	public static volatile SingularAttribute<Vehicle, LocalDateTime> createdAt;
	public static volatile SingularAttribute<Vehicle, Integer> year;
	public static volatile SingularAttribute<Vehicle, String> createdBy;
	public static volatile SingularAttribute<Vehicle, LocalDateTime> modifiedAt;
	public static volatile SingularAttribute<Vehicle, String> model;
	public static volatile SingularAttribute<Vehicle, String> modifiedBy;
	public static volatile SingularAttribute<Vehicle, Integer> id;
	public static volatile SingularAttribute<Vehicle, String> make;
	public static volatile SingularAttribute<Vehicle, Long> version;

}


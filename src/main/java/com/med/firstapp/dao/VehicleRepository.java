package com.med.firstapp.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.med.firstapp.model.Vehicle;

@Repository
@Transactional(readOnly = true)
public interface VehicleRepository extends JpaRepository<Vehicle, Integer>, VehicleRepositoryCustom
										, JpaSpecificationExecutor<Vehicle> // With the JPA Criteria API
										, QueryDslPredicateExecutor<Vehicle	> // Queries With Querydsl
{

	// CRUD methods on repository instances are transactional by default
	// @Transactional readOnly flag is set to true for reading operations and a plain @Transactional for other operations.

	public Vehicle findById(Integer id);

	public List<Vehicle> findByMake(String make);

	public List<Vehicle> findByMake(String make, Sort sort);

	@Query("SELECT v FROM Vehicle v WHERE v.make = :make AND v.model = :model")
	public List<Vehicle> findByMakeAndModel(@Param("make") String make, @Param("model") String model);

	@Query("SELECT v FROM Vehicle v WHERE v.make = :make AND v.year= :year")
	public List<Vehicle> findByMakeAndYear(@Param("make") String make, @Param("year") Integer year, Sort sort);

	@Query("SELECT v FROM Vehicle v WHERE v.make = :make AND v.year= :year")
	public List<Vehicle> findByMakeAndYear(@Param("make") String make, @Param("year") Integer year, Pageable pageable);

	@Modifying
	@Transactional
	@Query("UPDATE Vehicle v SET v.make = :make WHERE v.id = :id")
	public int updateMake(@Param("id") int id, @Param("make") String make);


}


//Google @NoRepositoryBean
//This ensures that Spring Data JPA doesn’t try to create an implementation for our base repository interface.

package com.med.firstapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.med.firstapp.model.Privilege;

@Repository
@Transactional(readOnly = true)
public interface PrivilegeRepository extends JpaRepository<Privilege, Long>, QueryDslPredicateExecutor<Privilege> {

	public Privilege findByName(String name);
}

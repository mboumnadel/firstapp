package com.med.firstapp.dao;

import com.med.firstapp.model.Employee;

public interface EmployeeDao extends AbstractDao<Integer, Employee> {

    void deleteByNumber(String number);

    Employee findByNumber(String number);

}

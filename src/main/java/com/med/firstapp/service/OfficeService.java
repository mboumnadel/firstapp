package com.med.firstapp.service;

import java.util.List;

import com.med.firstapp.model.Office;

public interface OfficeService {

	Office findOfficeById(int id);

	List<Office> findAllOffices(); 
    
}

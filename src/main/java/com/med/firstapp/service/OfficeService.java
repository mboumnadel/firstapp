package com.med.firstapp.service;

import java.util.List;

import com.med.firstapp.model.Office;

public interface OfficeService {

	Office findById(int id);

	List<Office> findAll(); 
}

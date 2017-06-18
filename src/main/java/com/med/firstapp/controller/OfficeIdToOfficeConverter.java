package com.med.firstapp.controller;

import org.springframework.core.convert.converter.Converter;

import com.med.firstapp.model.Office;
import com.med.firstapp.service.OfficeService;

public class OfficeIdToOfficeConverter implements Converter<String, Office> {

    private OfficeService officeService;

    public OfficeIdToOfficeConverter (OfficeService officeService) {
        this.officeService = officeService;
    }

    @Override
    public Office convert (String id) {
        try {
            int officeId = Integer.valueOf(id);
            return officeService.findById(officeId);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
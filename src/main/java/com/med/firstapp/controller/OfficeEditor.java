package com.med.firstapp.controller;

import java.beans.PropertyEditorSupport;

import com.med.firstapp.model.Office;
import com.med.firstapp.service.OfficeService;

public class OfficeEditor extends PropertyEditorSupport {

    private OfficeService officeService;
 
    public OfficeEditor(OfficeService officeService) {
        this.officeService = officeService;
        System.out.println(" OfficeEditor Constructor ");
    }

    @Override
	public String getAsText() {

    	Office office = (Office) getValue();

    	String ret = (office == null)?  null : office.getId().toString();
    	
    	System.out.println(" OfficeEditor getAsText returns:" + ret + ".");
    	
    	return ret;
	}

	@Override
    public void setAsText(String text) throws IllegalArgumentException {
    	
		System.out.println(" OfficeEditor setAsText text:" + text + ".");

    	if(text.equals("")){
    		setValue(null);
    		return;
    	}
    	int id = Integer.parseInt(text);
        Office office = officeService.findOfficeById(id);
        setValue(office);
    }

}
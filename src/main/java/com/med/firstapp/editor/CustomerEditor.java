package com.med.firstapp.editor;

import java.beans.PropertyEditorSupport;

import com.med.firstapp.model.Customer;
import com.med.firstapp.service.CustomerService;


public class CustomerEditor extends PropertyEditorSupport {

    private CustomerService customerService;
 
    public CustomerEditor(CustomerService customerService) {
        this.customerService = customerService;
        
        System.out.println(" CustomerEditor Constructor ");
    }

    @Override
	public String getAsText() {

    	Customer customer = (Customer) getValue();

    	String ret = (customer == null)?  null : customer.getId().toString();
    	
    	System.out.println(" CustomerEditor getAsText from object returns:" + ret + ".");
    	
    	return ret;
	}

	@Override
    public void setAsText(String text) throws IllegalArgumentException {
    	
		// REVIEW RELATIONS AND FETCH.MODE TO LAZY ??????s
		System.out.println(" CustomerEditor setAsText text:" + text + ". BEGIN");

    	if(text.equals("")){
    		setValue(null);
    		return;
    	}
    	int id = Integer.parseInt(text);
        Customer customer = customerService.findById(id);
        setValue(customer);
        System.out.println(" CustomerEditor setAsText text:" + text + ". END");
    }
}

package com.med.firstapp.controller;

import java.beans.PropertyEditorSupport;

import com.med.firstapp.model.Order;
import com.med.firstapp.service.OrderService;

public class OrderEditor extends PropertyEditorSupport {

    private OrderService orderService;
 
    public OrderEditor(OrderService orderService) {
        this.orderService = orderService;
        
        System.out.println(" OrderEditor Constructor ");
    }

    @Override
	public String getAsText() {

    	Order order = (Order) getValue();

    	String ret = (order == null)?  null : order.getId().toString();
    	
    	System.out.println(" OrderEditor getAsText from object returns:" + ret + ".");
    	
    	return ret;
	}

	@Override
    public void setAsText(String text) throws IllegalArgumentException {
    	
		System.out.println(" OrderEditor setAsText text:" + text + ". START");

    	if(text.equals("")){
    		setValue(null);
    		return;
    	}
    	int id = Integer.parseInt(text);
        Order order = orderService.findById(id);
        setValue(order);
        System.out.println(" OrderEditor setAsText text:" + text + ". END");
    }
}
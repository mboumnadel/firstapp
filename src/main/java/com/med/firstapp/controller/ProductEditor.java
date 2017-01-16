package com.med.firstapp.controller;

import java.beans.PropertyEditorSupport;

import com.med.firstapp.model.Product;
import com.med.firstapp.service.ProductService;

public class ProductEditor extends PropertyEditorSupport {

    private ProductService productService;
 
    public ProductEditor(ProductService productService) {
        this.productService = productService;
        
        System.out.println(" ProductEditor Constructor ");
    }

    @Override
	public String getAsText() {

    	Product product = (Product) getValue();

    	String ret = (product == null)?  null : product.getId().toString();
    	
    	System.out.println(" ProductEditor getAsText returns:" + ret + ".");
    	
    	return ret;
	}

	@Override
    public void setAsText(String text) throws IllegalArgumentException {
    	
		System.out.println(" ProductEditor setAsText text:" + text + ".");

    	if(text.equals("")){
    		setValue(null);
    		return;
    	}
    	int id = Integer.parseInt(text);
        Product product = productService.findProductById(id);
        setValue(product);
    }
}

package com.med.firstapp.editor;

import java.beans.PropertyEditorSupport;

import com.med.firstapp.model.Employee;
import com.med.firstapp.service.EmployeeService;

public class EmployeeEditor extends PropertyEditorSupport {

    private EmployeeService employeeService;

    public EmployeeEditor(EmployeeService employeeService) {
        this.employeeService = employeeService;

        System.out.println(" EmployeeEditor Constructor ");
    }

    @Override
	public String getAsText() {

    	Employee employee = (Employee) getValue();

    	String ret = (employee == null)?  null : employee.getId().toString();

    	System.out.println(" EmployeeEditor getAsText returns:" + ret + ".");

    	return ret;
	}

	@Override
    public void setAsText(String text) throws IllegalArgumentException {

		System.out.println(" EmployeeEditor setAsText text:" + text + ". BEGIN");

    	if(text.equals("")){
    		setValue(null);
    		return;
    	}
    	int id = Integer.parseInt(text);
        Employee employee = employeeService.findById(id);
        setValue(employee);
        System.out.println(" EmployeeEditor setAsText text:" + text + ". END");
    }
}
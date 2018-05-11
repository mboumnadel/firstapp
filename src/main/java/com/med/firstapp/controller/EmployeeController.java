package com.med.firstapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.med.firstapp.editor.EmployeeEditor;
import com.med.firstapp.editor.OfficeEditor;
import com.med.firstapp.model.Employee;
import com.med.firstapp.model.Office;
import com.med.firstapp.service.EmployeeService;
import com.med.firstapp.service.OfficeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private OfficeService officeService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {

		System.out.println("**** initBinder ***");

	    ///binder.setConversionService(conversionService);
	    //binder.addCustomFormatter(new OfficeFormatter(officeService));

		binder.registerCustomEditor(Office.class, new OfficeEditor(officeService));
		binder.registerCustomEditor(Employee.class, new EmployeeEditor(employeeService));
	}

	@ModelAttribute
	public void getJobTitles(Model model){
		List<String> jobTitles = new ArrayList<>();
		jobTitles.add("Accountant");
		jobTitles.add("Manager");
		jobTitles.add("IT Professional");
		jobTitles.add("Pay Master");

		model.addAttribute("jobTitles", jobTitles);
	}

	@RequestMapping(value = {"", "/", "list"}, method = RequestMethod.GET)
	public String listEmployees(ModelMap model) {

		List<Employee> findAllEmployees = employeeService.findAll();

        model.addAttribute("employees", findAllEmployees);

		return "employee-list";

	}

	@RequestMapping(value = {"/new"}, method = RequestMethod.GET)
	public String newEmployee(ModelMap model) {

		Employee employee = new Employee();
        model.addAttribute("employee", employee);
        List<Office> offices = officeService.findAll();
        List<Employee> employees = employeeService.findAll();

        model.addAttribute("offices", offices);
        model.addAttribute("employees", employees);

		return "employee-edit";
	}

	@RequestMapping(value = {"/edit/{id}"}, method = RequestMethod.GET)
	public String editEmployee(@PathVariable int id, ModelMap model) {

		Employee employee = employeeService.findById(id);

        model.addAttribute("employee", employee);

        List<Office> offices = officeService.findAll();
        model.addAttribute("offices", offices);

        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);

		return "employee-edit";
	}

	@PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
	@RequestMapping(value = {"/save"}, method = RequestMethod.POST)
	public String saveEmployee(@Valid @ModelAttribute Employee employee, BindingResult result, ModelMap model){

        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);

        List<Office> offices = officeService.findAll();
        model.addAttribute("offices", offices);

        if(result.hasErrors()) {
        	model.addAttribute("saveEmployeeStatus", false);
        	model.addAttribute("msg", result.getAllErrors());

        	return "employee-edit";
        }

       employeeService.merge(employee);

        model.addAttribute("saveEmployeeStatus", true);
        return "employee-edit";
    }

}


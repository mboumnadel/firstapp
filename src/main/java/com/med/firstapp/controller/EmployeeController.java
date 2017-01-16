package com.med.firstapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.med.firstapp.model.Employee;
import com.med.firstapp.model.Office;
import com.med.firstapp.service.EmployeeService;
import com.med.firstapp.service.OfficeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	private static int counter = 0;

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private OfficeService officeService;
	
	//private String message;

	
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    
	    ///binder.setConversionService(conversionService);
	    //binder.addCustomFormatter(new OfficeFormatter(officeService));
		
		System.out.println("**** initBinder ***");
		
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
		
		List<Employee> findAllEmployees = employeeService.findAllEmployees();

        model.addAttribute("employees", findAllEmployees);
		
		return "employee-list";
		
	}

	@RequestMapping(value = {"/new"}, method = RequestMethod.GET)
	public String newEmployee(ModelMap model) {

		Employee employee = new Employee();
        model.addAttribute("employee", employee);
        List<Office> offices = officeService.findAllOffices();
        List<Employee> employees = employeeService.findAllEmployees();
        
        
        model.addAttribute("offices", offices);
        model.addAttribute("employees", employees);

		return "employee-edit";
	}
	
	@RequestMapping(value = {"/edit/{id}"}, method = RequestMethod.GET)
	public String editEmployee(@PathVariable int id, ModelMap model) {
		
		Employee employee = employeeService.findEmployeeById(id);
        model.addAttribute("employee", employee);

        List<Office> offices = officeService.findAllOffices();
        model.addAttribute("offices", offices);

        List<Employee> employees = employeeService.findAllEmployees();
        model.addAttribute("employees", employees);

        System.out.println("** inside editEmployee **");
        
		return "employee-edit";
	}

	@RequestMapping(value = {"/save"}, method = RequestMethod.POST)
	public String saveEmployee(@Valid @ModelAttribute Employee employee, BindingResult result, ModelMap model){

        List<Employee> employees = employeeService.findAllEmployees();
        model.addAttribute("employees", employees);

        List<Office> offices = officeService.findAllOffices();
        model.addAttribute("offices", offices);

        if(result.hasErrors()) {
        	model.addAttribute("saveEmployeeStatus", false);
        	model.addAttribute("msg", result.getAllErrors());

        	return "employee-edit";
        }

        employeeService.saveOrUpdateEmployee(employee);
        

        model.addAttribute("saveEmployeeStatus", true);
        return "employee-edit";
    }
	
	@RequestMapping(value = {"/oldlist"}, method = RequestMethod.GET)
	public String list(ModelMap model) {

		ArrayList<Employee> employees = new ArrayList<>();
		Employee e1 = new Employee();
		e1.setEmail("employee1@company.com");
		e1.setExtension("x1000");
		e1.setFirstName("Employee First 1");
		e1.setJobTitle("Manager");
		e1.setLastName("Employee Name 1");
		e1.setNumber("0001");
		
		Employee e2 = new Employee();
		e2.setEmail("employee2@company.com");
		e2.setExtension("x2000");
		e2.setFirstName("Employee First 2");
		e2.setJobTitle("Accountant");
		e2.setLastName("Employee Name 2");
		e2.setNumber("0002");
		
		employees.add(e1);
		employees.add(e2);
		


        model.addAttribute("employees", employees);
		
		return "employee-list";
	}

	@RequestMapping(value = "/AAA/**", method = RequestMethod.GET)
	public String catchall(ModelMap model) {

		model.addAttribute("message", "employee /AAA/** : ");
		model.addAttribute("counter", ++counter);
		return "base";

	}
	
}


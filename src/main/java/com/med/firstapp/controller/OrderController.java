package com.med.firstapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.med.firstapp.model.Customer;
import com.med.firstapp.model.Order;
import com.med.firstapp.model.Product;
import com.med.firstapp.service.CustomerService;
import com.med.firstapp.service.OrderService;
import com.med.firstapp.service.ProductService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = {"/search"}, method = RequestMethod.POST)
	public String orderSearchResult(@RequestParam("customer") int customerId, ModelMap model) {

		List<Order> orders = orderService.getOrdersByCustomerId(customerId);

        model.addAttribute("orders", orders);

		return "order-list";
	}
	
	
	@RequestMapping(value = {"/search"}, method = RequestMethod.GET)
	public String orderSearch(ModelMap model) {

		Order order = new Order();
		model.addAttribute("order", order);
		
        List<Customer> customers = customerService. findAllCustomers();
        model.addAttribute("customers", customers);

        
		return "order-search";
	}
	
	@RequestMapping(value = "edit/{orderId}", method = RequestMethod.GET)
	public String editOrder(@PathVariable int orderId, ModelMap model){
		
		Order order = orderService.findOrderAndDetailsById(orderId);
		model.addAttribute("order", order);
		
		List<Product> products = productService.findAllProducts();
		model.addAttribute("products", products);

		List<Customer> customers = customerService. findAllCustomers();
        model.addAttribute("customers", customers);

		return "order-edit";
	}
	
	@InitBinder(value = "order")
	protected void initBinder(WebDataBinder binder) {
	    
	    ///binder.setConversionService(conversionService);
	    //binder.addCustomFormatter(new OfficeFormatter(officeService));
//		binder.registerCustomEditor(Office.class, new OfficeEditor(officeService));
//		binder.registerCustomEditor(Employee.class, new EmployeeEditor(employeeService));
		
		System.out.println("++ order controller init binder value order ++++++");
		
		//binder.setDisallowedFields("customer");
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
		
		binder.registerCustomEditor(Customer.class, new CustomerEditor(customerService));
		binder.registerCustomEditor(Product.class, new ProductEditor(productService));

		binder.registerCustomEditor(Order.class, new OrderEditor(orderService));
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String saveOrder(@ModelAttribute(value = "order") Order order, Model model){
		
		
		orderService.updateOrder(order);
		
		
		List<Customer> customers = customerService. findAllCustomers();
        model.addAttribute("customers", customers);
		
		return "order-edit";
	}
}

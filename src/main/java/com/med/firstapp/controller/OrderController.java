package com.med.firstapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
	
	Logger logger = LogManager.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ProductService productService;
		
	@RequestMapping(value = {"/search"}, method = RequestMethod.GET)
	public String orderSearch(ModelMap model) {

		Order order = new Order();
		model.addAttribute("order", order);

		System.out.println("orderController customerService. findAllCustomers()");
		logger.error("orderController customerService. findAllCustomers()");
        List<Customer> customers = customerService. findAll();
        model.addAttribute("customers", customers);

		return "order-search";
	}

	@RequestMapping(value = {"/search"}, method = RequestMethod.POST)
	public String orderSearchResult(@RequestParam("customer") int customerId, ModelMap model) {

		logger.error("orderSearchResult before orderService.getOrdersByCustomerId(customerId)");
		List<Order> orders = orderService.getOrdersByCustomerId(customerId);

		logger.error("orderSearchResult after orderService.getOrdersByCustomerId(customerId)");
        model.addAttribute("orders", orders);

		return "order-list";
	}
	
	@Transactional
	@RequestMapping(value = "edit/{orderId}", method = RequestMethod.GET)
	public String editOrder(@PathVariable int orderId, ModelMap model){
		
		logger.error("Logger editOrder before orderService.findOrderAndDetailsById(orderId)");
		
		Order order = orderService.findOrderAndDetailsById(orderId);
		model.addAttribute("order", order);
		
		logger.error("Logger editOrder Method before productService.findAllProducts");
		
		List<Product> products = productService.findAll();
		model.addAttribute("products", products);

		logger.error("Logger editOrder before customerService.findAllCustomers");

		List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);

		return "order-edit";
	}
	
	@Transactional
	@InitBinder(value = "order")
	protected void initBinder(WebDataBinder binder) {
	    
	    ///binder.setConversionService(conversionService);
	    //binder.addCustomFormatter(new OfficeFormatter(officeService));
//		binder.registerCustomEditor(Office.class, new OfficeEditor(officeService));
//		binder.registerCustomEditor(Employee.class, new EmployeeEditor(employeeService));
		
		System.out.println("++ order controller init binder value order ++++++");
		
		
		
		//binder.setDisallowedFields("customer");
		
		//binder.setDisallowedFields(new String[] {"orderDetails"});
		
		logger.error("Logger initBinder before new CustomDateEditor");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
		
		logger.error("Logger initBinder before new CustomerEditor");
		binder.registerCustomEditor(Customer.class, new CustomerEditor(customerService));
		
		logger.error("Logger initBinder before new ProductEditor");
		binder.registerCustomEditor(Product.class, new ProductEditor(productService));

		logger.error("Logger initBinder before new OrderEditor");
		binder.registerCustomEditor(Order.class, new OrderEditor(orderService));
	}

	@Transactional
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String saveOrder(@ModelAttribute(value = "order") Order order, Model model, HttpServletRequest req){

		logger.error("Logger saveOrder Method");
		
		System.out.println("+++ saveOrder +++");
		
		Enumeration<String> parameterNames = req.getParameterNames();
		
		while(parameterNames.hasMoreElements()) {
			//String name = parameterNames.nextElement();
		    //System.out.println(name + ":" + req.getParameter(name));
		}
		
		//order.setOrderDetails(null);
		
//		System.out.println("order: " + order.getId() + " " + order.getNumber());
		
//		for(OrderDetails orderDetails : order.getOrderDetails()){
//			System.out.println("orderDetails: " + orderDetails.getId() + " " + orderDetails.getPriceEach());
//			System.out.println("orderDetails Order: " + orderDetails.getOrder());
//		}
		
		//System.out.println(order);
		
		
		
		//orderService.updateOrder(order);
		
		//model.addAttribute("order", new Order());
		
//		List<Product> products = productService.findAllProducts();
//		model.addAttribute("products", products);
		
//		List<Customer> customers = customerService. findAllCustomers();
//        model.addAttribute("customers", customers);
		
		return "order-edit";
	}
}

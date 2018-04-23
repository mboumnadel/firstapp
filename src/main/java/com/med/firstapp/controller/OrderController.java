package com.med.firstapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.med.firstapp.editor.CustomerEditor;
import com.med.firstapp.editor.OrderEditor;
import com.med.firstapp.editor.ProductEditor;
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

	@InitBinder(value = "order")
	protected void initBinder(WebDataBinder binder) {

	    ///binder.setConversionService(conversionService);
	    //binder.addCustomFormatter(new OfficeFormatter(officeService));
		//binder.registerCustomEditor(Office.class, new OfficeEditor(officeService));
		//binder.registerCustomEditor(Employee.class, new EmployeeEditor(employeeService));

		//binder.setDisallowedFields("customer");
		//binder.setDisallowedFields(new String[] {"orderDetails"});

		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));

		binder.registerCustomEditor(Customer.class, new CustomerEditor(customerService));

		binder.registerCustomEditor(Product.class, new ProductEditor(productService));

		binder.registerCustomEditor(Order.class, new OrderEditor(orderService));
	}

	@RequestMapping(value = {"/search"}, method = RequestMethod.GET)
	public String orderSearch(ModelMap model) {

		Order order = new Order();
		model.addAttribute("order", order);

        List<Customer> customers = customerService. findAll();
        model.addAttribute("customers", customers);

		return "order-search";
	}

	@RequestMapping(value = {"/search"}, method = RequestMethod.POST)
	public String orderSearchResult(@RequestParam("customer") int customerId, ModelMap model) {

		List<Order> orders = orderService.getOrdersByCustomerId(customerId);

        model.addAttribute("orders", orders);

		return "order-list";
	}

	@RequestMapping(value = "edit/{orderId}", method = RequestMethod.GET)
	public String editOrder(@PathVariable int orderId, ModelMap model){

		Order order = orderService.findOrderAndDetailsById(orderId);
		model.addAttribute("order", order);

		List<Product> products = productService.findAll();
		model.addAttribute("products", products);

		List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);

		return "order-edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String saveOrder(@Valid  @ModelAttribute(value = "order") Order order,BindingResult result,  Model model, HttpServletRequest req){

		List<Product> products = productService.findAll();
		model.addAttribute("products", products);

		List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);

		if (result.hasErrors()) {
			return "order-edit";
		}

		orderService.merge(order);

		return "order-edit";
	}

}

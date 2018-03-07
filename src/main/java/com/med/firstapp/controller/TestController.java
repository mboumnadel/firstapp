package com.med.firstapp.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.med.firstapp.model.Customer;
import com.med.firstapp.model.Order;
import com.med.firstapp.service.CustomerService;
import com.med.firstapp.service.DummyBean;
import com.med.firstapp.service.DummyService;
import com.med.firstapp.service.OrderService;

@Controller
@RequestMapping("/test")
public class TestController {

	//http://stackoverflow.com/questions/4396284/does-spring-transactional-attribute-work-on-a-private-method
	//http://blog.jhades.org/how-does-spring-transactional-really-work/
	//http://blog.jhades.org/setup-and-gotchas-of-the-hibernate-second-level-and-query-caches/
	//http://www.baeldung.com/spring-requestmapping

	@Autowired
	private OrderService orderService;

	@Autowired
	private CustomerService customerService;

	@Autowired(required = false)
	private DummyBean dummyBean;

	@Autowired
	private DummyService dummyService;

	@RequestMapping(value = {"/testDummyService/{str}"}, method = RequestMethod.GET)
	@ResponseBody
	public String testDummyService(@PathVariable String str, ModelMap model){

		return dummyService.getData(str);
	}

	@RequestMapping(value = {"/testDummyBean"}, method = RequestMethod.GET)
	@ResponseBody
	public String testDummyBean(ModelMap model) {

		return dummyBean.getData("wassup");
	}


	@RequestMapping(value = {"/testOrderSearch"}, method = RequestMethod.GET)
	public String testOrderSearch(ModelMap model) {

		Order order = new Order();
		model.addAttribute("order", order);

        List<Customer> customers = customerService. findAll();
        model.addAttribute("customers", customers);

		return "order-search";
	}

	@RequestMapping(value = "testEditOrder/{orderId}", method = RequestMethod.GET)
	public String testEditOrder(@PathVariable int orderId, ModelMap model){

		Order order = orderService.findOrderAndDetailsById(orderId);
		model.addAttribute("order", order);

		List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);

		return "order-edit";
	}

	@RequestMapping(value = "testSaveOrder", method = RequestMethod.POST)
	public String testSaveOrder(@Valid  @ModelAttribute(value = "order") Order order, BindingResult result,  Model model, HttpServletRequest req){

		if (result.hasErrors()) {
			System.out.println("-------- testSaveOrder error -------");
			List<ObjectError> errors = result.getAllErrors();
			for(ObjectError error :errors){
				System.out.println(error.toString());
			}
			return "order-edit";
		}

		System.out.println("------- testSaveOrder start ------");
		System.out.println(order.toString());


		Map<String, String[]> parameterMap = req.getParameterMap();
		for (Entry<String, String[]> entry : parameterMap.entrySet())
		{
		    System.out.println(entry.getKey() + " / " + Arrays.toString(entry.getValue()));
		}

		System.out.println("------- testSaveOrder end ------");

		return "order-edit";
	}

}

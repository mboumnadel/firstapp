package com.med.firstapp.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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

import com.med.firstapp.dao.VehicleRepository;
import com.med.firstapp.model.Customer;
import com.med.firstapp.model.Order;
import com.med.firstapp.model.Vehicle;
import com.med.firstapp.service.CustomerService;
import com.med.firstapp.service.DummyBean;
import com.med.firstapp.service.DummyService;
import com.med.firstapp.service.OrderService;
import com.med.firstapp.service.OwnerService;
import com.med.firstapp.service.UserService;
import com.med.firstapp.service.VehicleService;

@Controller
@RequestMapping("/test")
public class TestController {

	//http://stackoverflow.com/questions/4396284/does-spring-transactional-attribute-work-on-a-private-method
	//http://blog.jhades.org/how-does-spring-transactional-really-work/
	//http://blog.jhades.org/setup-and-gotchas-of-the-hibernate-second-level-and-query-caches/
	//http://www.baeldung.com/spring-requestmapping

	@Autowired
    private UserService userService;

    @Autowired
    private VehicleRepository vehicleRepository;

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private OwnerService ownerService;

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

	@RequestMapping(value = "testSaveVehicle", method = RequestMethod.POST)
	public String testSaveVehicle(@Valid  @ModelAttribute(value = "vehicle") Vehicle vehicle, BindingResult result,  Model model){

		if (result.hasErrors()) {
			System.out.println("-------- testSaveVehicle error -------");
			List<ObjectError> errors = result.getAllErrors();
			for(ObjectError error :errors){
				System.out.println(error.toString());
			}
			return "test_trans";
		}

		System.out.println("------- testSaveVehicle start ------");
		System.out.println("vehicle.toString() " + vehicle.toString());
		vehicleService.merge(vehicle);
		System.out.println("------- testSaveVehicle end ------");

		return "test_trans";
	}

	@RequestMapping(value = {"/testTrans/1"})
	public String testNoTransLoadSameEntityTwicewice(){

		System.out.println("---- starting TestController");

		vehicleService.testNoTransLoadSameEntityTwice();

		System.out.println("---- End TestController");

		return "test_trans";
	}

	@RequestMapping(value = {"/testTrans/2"})
	public String testTransLoadSameEntityTwice(){

		System.out.println("---- starting testTransLoadSameEntityTwice TestController");

		vehicleService.testTransLoadSameEntityTwice();

		System.out.println("---- End testTransLoadSameEntityTwice TestController");

		return "test_trans";
	}

	@RequestMapping(value = {"/testTrans/3"})
	public String testTransLoadSameEntityTwice2(){

		//First Level Cache is NOT working ACROOS transactions
		//First Level Cache is working and caching entity ONLY within a transaction
		//One SQL query is issued to DB for every call to the service
		//Same entityManager

		System.out.println("---- starting testTransLoadSameEntityTwice2 TestController");

		vehicleService.testTransLoadSameEntityTwice();

		System.out.println("---- between testTransLoadSameEntityTwice2 TestController");

		vehicleService.testTransLoadSameEntityTwice();

		System.out.println("---- End testTransLoadSameEntityTwice2 TestController");

		return "test_trans";
	}

	@RequestMapping(value = {"/testTrans/4"})
	public String testTwoServices(ModelMap model) {

		//Every Service gets injected a different entityManager

		System.out.println("---- starting testTwoServices TestController");

		vehicleService.testTransLoadSameEntityTwice();

		System.out.println("---- between testTwoServices TestController");

		ownerService.testTransLoadSameEntityTwice();

		System.out.println("---- End testTwoServices TestController");

		return "test_trans";
	}

	@RequestMapping(value = {"/testTrans/5"})
	public String testTransTwoDaos() throws Exception{

		System.out.println("---- starting testTransTwoDaos TestController");

		ownerService.testTransTwoDaos();

		System.out.println("---- End testTransTwoDaos TestController");

		return "test_trans";
	}

	@RequestMapping(value = {"/testGetVehicles"}, method = RequestMethod.GET)
	public String testGetVehicles(@PageableDefault(size = 10, page = 1) Pageable pageable, ModelMap model) throws IOException {

		//?page=2&size=3&sort=make&sort=model,asc

    	Page<Vehicle> thepage = vehicleRepository.findAll(pageable);

        System.out.println(" getSize " + thepage.getSize());
        System.out.println(" getNumber " + thepage.getNumber());
        System.out.println(" getNumberOfElements " + thepage.getNumberOfElements());
        System.out.println(" getTotalElements " + thepage.getTotalElements());
        System.out.println(" getTotalPages " + thepage.getTotalPages());

        thepage.forEach(System.out::println);

        model.addAttribute("vehicles", thepage.getContent());

		return "test_trans";
	}

	@RequestMapping(value = {"/testAddNewVehicle"}, method = RequestMethod.GET)
	public String testAddNewVehicle(ModelMap model) {
		System.out.println("------- testAddNewVehicle start ------");

//		Vehicle vehicle = new Vehicle();
//		vehicle.setMake("Citroen");
//		vehicle.setModel("XMAX 400");
//		vehicle.setYear(2018);
//		System.out.println("------- before save new vehicle ------");
//		vehicleRepository.save(vehicle);

		System.out.println("------- before fetcing vehicle ------");
		Vehicle vehicle2 = vehicleRepository.findById(396);
		System.out.println("------- before saving existing vehicle ------");
		vehicle2.setModel("new model");
		vehicleRepository.save(vehicle2);
		System.out.println("------- testAddNewVehicle end ------");


        return "test_trans";
	}

	@RequestMapping(value = {"/testCreateUser"}, method = RequestMethod.GET)
	public String testCreateUser(ModelMap model) {
		System.out.println("------- testCreateUser start ------");

		userService.createUser();

		System.out.println("------- testCreateUser end  ------");

		return "test_trans";
	}

	@RequestMapping(value = {"/testUnsecured"}, method = RequestMethod.GET)
	public String testUnsecured(ModelMap model, Authentication authentication) {
		System.out.println("------- testUnsecured start ------");

		if(authentication != null){
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			System.out.println("authorities :" + authorities);

			Object credentials = authentication.getCredentials();
			System.out.println("credentials :" + credentials);

			Object details = authentication.getDetails();
			System.out.println("details :" + details);

			String name = authentication.getName();
			System.out.println("name :" + name);
		} else {
			System.out.println("authentication is null");
		}

		System.out.println("------- testUnsecured end  ------");

		return "test_trans";
	}

	@PreAuthorize("authenticated")
	@RequestMapping(value = {"/testPreAuthorizeAuthenticated"}, method = RequestMethod.GET)
	public String testPreAuthorizeAuthenticated(ModelMap model, Authentication authentication) {
		System.out.println("------- testPreAuthorizeAuthenticated start ------");

		if(authentication != null){
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			System.out.println("authorities :" + authorities);


			Object credentials = authentication.getCredentials();
			System.out.println("credentials :" + credentials);


			Object details = authentication.getDetails();
			System.out.println("details :" + details);

			String name = authentication.getName();
			System.out.println("name :" + name);
		} else {
			System.out.println("authentication is null");
		}

		System.out.println("------- testPreAuthorizeAuthenticated End ------");

		return "test_trans";
	}
}

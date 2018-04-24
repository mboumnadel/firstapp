package com.med.firstapp.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.med.firstapp.dao.VehiclePredicates;
import com.med.firstapp.dao.VehicleRepository;
import com.med.firstapp.dao.VehicleSpecifications;
import com.med.firstapp.model.Employee;
import com.med.firstapp.model.Order;
import com.med.firstapp.model.QVehicle;
import com.med.firstapp.model.Vehicle;
import com.med.firstapp.service.DummyBean;
import com.med.firstapp.service.DummyService;
import com.med.firstapp.service.EmployeeService;
import com.med.firstapp.service.VehicleService;
import com.querydsl.core.types.OrderSpecifier;

@RunWith(SpringJUnit4ClassRunner.class)


//@ContextConfiguration(locations = {"classpath:spring-servlet.xml"})
// classpath points to src/main/resources or src/test/resources
// "file:src/main/webapp/WEB-INF/applicationContext.xml",

@ContextConfiguration(locations = {
		"classpath:applicationContext.xml",
		"classpath:applicationContext-test.xml",
		"classpath:applicationContext-persistence-test.xml",
		"classpath:applicationContext-web.xml"
		})


@WebAppConfiguration // will load WebApplicationContext
//we load WebApplicationContext and MockServletContext using the @WebAppConfiguration annotation and inject their instances using @Autowired.


@TestExecutionListeners(
	//listeners = { DbUnitTestExecutionListener.class },
	listeners = {
				  ServletTestExecutionListener.class
				, DirtiesContextBeforeModesTestExecutionListener.class
				, DependencyInjectionTestExecutionListener.class
				, DirtiesContextTestExecutionListener.class
				//, TransactionalTestExecutionListener.class
				, SqlScriptsTestExecutionListener.class
				,TransactionDbUnitTestExecutionListener.class
				}
			// , mergeMode = MergeMode.MERGE_WITH_DEFAULTS
)






//@DbUnitConfiguration(databaseConnection={"dataSource"})

//@Transactional
//@Rollback
public class ITTestControllerTest {

	private MockMvc mockMvc;

    //@Autowired
    //private TodoService todoServiceMock;

	@PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    protected MockServletContext mockServletContext;


    @Autowired
	private DummyService dummyService;

    @Autowired
	private DummyBean dummyBean; // Mock of DummyBean will be loaded in applicationContext-test.xml and injected

    @Autowired
	private EmployeeService employeeService;

    @Autowired
	private VehicleService vehicleService;

    @Autowired
    private VehicleRepository vehicleRepository;


    @Before
    public void setUp() {
       // Mockito.reset(todoServiceMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    @DatabaseSetup("/vehicle-first-10.xml")
    public void test_VehicleRepositoryImpl() throws UnsupportedEncodingException, Exception {
    	System.out.println("---------- testtest_VehicleRepositoryImpl @TEST ---------");

    	Integer id = 190;

    	Vehicle vehicle1 = vehicleRepository.findByIdUsingJpa(id);
    	System.out.println("vehicle1 " + vehicle1);

    	Vehicle vehicle2 = vehicleRepository.findByIdUsingJpaCriteria(id);
    	System.out.println("vehicle2 " + vehicle2);

    	Vehicle vehicle3 = vehicleRepository.findByIdUsingJpaQuerydsl(id);
    	System.out.println("vehicle3 " + vehicle3);

    	System.out.println("---------- test_VehicleRepositoryImpl @TEST ---------");
    }

    @Test
    @DatabaseSetup("/vehicle-first-10.xml")
	public void testGetVehicles() throws UnsupportedEncodingException, Exception {

    	System.out.println("---------- testGetVehicles @TEST ---------");

    	mockMvc.perform(
				get("/test/testGetVehicles")///?page=0&size=5&sort=make&sort=model,asc
				.param("page", "0")
				.param("size", "5")
				.param("sort", "make")
				.param("sort", "model,asc")
			)
			.andExpect(model().attribute("vehicles", hasSize(5)))
			.andDo(print())
			;

    		//.andReturn().getModelAndView().getModel().get("vehicles")

		System.out.println("after calling mockmvc @TEST ");
    }

    @Test
    @DatabaseSetup("/vehicle-first-10.xml")
	public void testGetVehicleJPA_VehiclePredicates() throws UnsupportedEncodingException, Exception {
    	System.out.println("---------- testGetVehicleJPA JpaSpecificationExecutor @TEST start ---------");

    	System.out.println("before findAll(VehiclePredicates.hasMake(Acura)) ");
    	Iterable<Vehicle> list = vehicleRepository.findAll(VehiclePredicates.hasMake("Acura"));
    	list.forEach(System.out::println);

    	System.out.println("before findAll(VehiclePredicates.hasModel(RDX)) ");
    	Iterable<Vehicle> list2 = vehicleRepository.findAll(VehiclePredicates.hasModel("RDX"));
    	list2.forEach(System.out::println);

    	System.out.println("before findAll(VehiclePredicates.makeIsAndModelIsNot(Acura, RDX)) ");
    	Iterable<Vehicle> list3 = vehicleRepository.findAll(VehiclePredicates.makeIsAndModelIsNot("Acura", "RDX"));
    	list3.forEach(System.out::println);

    	OrderSpecifier<String> orderSpecifier = QVehicle.vehicle.model.desc();
    	System.out.println("before findAll(VehiclePredicates.makeIsAndModelIsNot(Acura, RDX)) ");
    	Iterable<Vehicle> list4 = vehicleRepository.findAll(VehiclePredicates.makeIsAndModelIsNot("Acura", "RDX"), orderSpecifier);
    	list4.forEach(System.out::println);

    	System.out.println("---------- testGetVehicleJPA JpaSpecificationExecutor @TEST end ---------");
    }

    @Test
    @DatabaseSetup("/vehicle-first-10.xml")
	public void testGetVehicleJPA_QueryDsl_JpaSpecificationExecutor() throws UnsupportedEncodingException, Exception {

    	System.out.println("---------- testGetVehicleJPA JpaSpecificationExecutor @TEST start ---------");

    	System.out.println("before findAll(VehicleSpecifications.hasMake(Acura)) ");
    	List<Vehicle> list = vehicleRepository.findAll(VehicleSpecifications.hasMake("Acura"));
    	list.stream().forEach(System.out::println);

    	System.out.println("before findAll(VehicleSpecifications.hasModel(RDX)) ");
    	List<Vehicle> list2 = vehicleRepository.findAll(VehicleSpecifications.hasModel("RDX"));
    	list2.stream().forEach(System.out::println);

    	System.out.println("before findAll(VehicleSpecifications.makeIsAndModelIsNot(Acura, RDX)) ");
    	List<Vehicle> list3 = vehicleRepository.findAll(VehicleSpecifications.makeIsAndModelIsNot("Acura", "RDX"));
    	list3.stream().forEach(System.out::println);

    	Sort sort = new Sort(new Sort.Order(Direction.DESC, "model"));
    	System.out.println("before findAll(VehicleSpecifications.makeIsAndModelIsNot(Acura, RDX, sort)) ");
    	List<Vehicle> list4 = vehicleRepository.findAll(VehicleSpecifications.makeIsAndModelIsNot("Acura", "RDX"), sort);
    	list4.stream().forEach(System.out::println);


    	PageRequest pageRequest = new PageRequest(2, 2, sort);
    	System.out.println("before findAll(VehicleSpecifications.hasMake(Acura)),pageRequest ");
    	Page<Vehicle> page = vehicleRepository.findAll(VehicleSpecifications.hasMake("Acura"), pageRequest);
    	page.forEach(System.out::println);

    	System.out.println("---------- testGetVehicleJPA JpaSpecificationExecutor @TEST end ---------");
    }

    @Test
    @DatabaseSetup("/vehicle-first-10.xml")
	public void testGetVehicleJPA_Sort_Paginatoin() throws UnsupportedEncodingException, Exception {
    	System.out.println("---------- testGetVehicleJPA Sort_Paginatoin @TEST start ---------");

    	Sort sort = new Sort(new Sort.Order(Direction.ASC, "model"));
    	System.out.println("before findAll(sort) ");
    	List<Vehicle> list = vehicleRepository.findAll(sort);
    	list.stream().forEach(System.out::println);



    	Sort sort2 = new Sort(Sort.Direction.DESC, "make").and(new Sort(Sort.Direction.ASC, "model"));
    	System.out.println("before findByMake(Acura, sort) ");
    	List<Vehicle> list2 = vehicleRepository.findByMake("Acura", sort2);
    	list2.stream().forEach(System.out::println);

    	Sort sort3 = new Sort(Sort.Direction.DESC, "make").and(new Sort(Sort.Direction.DESC, "model"));
    	System.out.println("before findByMakeAndYear(Acura, 2012, sort) ");
    	List<Vehicle> list3 = vehicleRepository.findByMakeAndYear("Acura", 2012, sort3);
    	list3.stream().forEach(System.out::println);

    	PageRequest pageRequest = new PageRequest(1, 3, sort3);
    	System.out.println("before findAll(pageRequest) ");
    	Page<Vehicle> page = vehicleRepository.findAll(pageRequest);
    	page.forEach(System.out::println);

    	PageRequest pageRequest2 = new PageRequest(2, 2, sort3);
    	System.out.println("before findAll(pageRequest) ");
    	List<Vehicle> list4 = vehicleRepository.findByMakeAndYear("Acura", 2012, pageRequest2);
    	list4.stream().forEach(System.out::println);

    	System.out.println("---------- testGetVehicleJPA Sort_Paginatoin @TEST end ---------");
    }

    @Test
    @DatabaseSetup("/vehicles.xml")
	public void testGetVehicleJPA_NoTrans() throws UnsupportedEncodingException, Exception {

    	System.out.println("---------- testGetVehicleJPA NoTrans @TEST start ---------");

    	Vehicle vehicle = vehicleRepository.findById(2);

    	if(vehicle != null){ System.out.println("findById(2) is: " + vehicle.toString()); }
    	else { System.out.println("findById(2) not found "); }

    	vehicle.setMake("ABCDEF");


    	System.out.println("before delete(1) ");
    	vehicleRepository.delete(1);

    	System.out.println("before delete(2) ");
    	vehicleRepository.delete(2);


    	System.out.println("before updateMake ");
    	Integer count = vehicleRepository.updateMake(2, "Hatch");
    	System.out.println("count is " + count);


    	System.out.println("before findAll() ");
    	List<Vehicle> list = vehicleRepository.findAll(); // Will NOT refresh vehicle id 2

    	if(list != null){ System.out.println("list is: " + list.toString()); }
    	else { System.out.println("list not found "); }

    	System.out.println("---------- testGetVehicleJPA NoTrans @TEST end ---------");
    }

    @Test
    @Transactional
    @DatabaseSetup("/vehicles.xml")
    @Rollback(value=false)
	public void testGetVehicleJPA() throws UnsupportedEncodingException, Exception {

    	System.out.println("---------- testGetVehicleJPA  @TEST start ---------");

    	Vehicle vehicle = vehicleRepository.findById(2);

    	if(vehicle != null){ System.out.println("findById(2) is: " + vehicle.toString()); }
    	else { System.out.println("findById(2) not found "); }

    	vehicle.setMake("ABCDEF");

    	Integer count = vehicleRepository.updateMake(2, "Hatch");
    	System.out.println("count is " + count);

    	List<Vehicle> list = vehicleRepository.findAll(); // Will NOT refresh vehicle id 2

    	if(list != null){ System.out.println("list is: " + list.toString()); }
    	else { System.out.println("list not found "); }


    	assertThat(vehicle.getMake(), equalTo("ABCDEF"));

    	System.out.println("---------- testGetVehicleJPA  @TEST end ---------");
    }

    @Test
    @DatabaseSetup("/vehicles.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED, value = "/expected_vehicles.xml")
	public void testSaveVehicle() throws UnsupportedEncodingException, Exception {

    	System.out.println("---------- testSaveVehicle @TEST ---------");

    	int vehicleId = 1;
    	Vehicle vehicle = vehicleService.findById(vehicleId);
		System.out.println("vehicle.toString() @TEST : " + vehicle.toString());

		String model = vehicle.getModel() + " TEST";
		vehicle.setModel(model);

		System.out.println("before calling mockmvc @TEST ");

		mockMvc.perform(
				post("/test/testSaveVehicle")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", String.valueOf(vehicle.getId()))
				.param("make", String.valueOf(vehicle.getMake()))
				.param("model", String.valueOf(vehicle.getModel()))
				.param("year", String.valueOf(vehicle.getYear()))
				.param("version", String.valueOf(vehicle.getVersion()))

			)
			.andExpect(model().hasNoErrors())

			.andExpect(model().attribute("vehicle",
					allOf(
							hasProperty("id", equalTo(vehicleId)),
							hasProperty("model", equalTo(model))
					)
			))
			;

		System.out.println("after calling mockmvc @TEST ");
    }


    @Ignore
    @Test
    //@ExpectedDatabase("employees.xml")
	public void testEmployeeSave() throws UnsupportedEncodingException, Exception {

    	long threadId = Thread.currentThread().getId();
        System.out.println("Thread # " + threadId + " in testEmployeeSave");

    	System.out.println("---------- testEmployeeSave ---------");

    	int employeeId = 49;
		Employee employee = employeeService.findById(employeeId);

		System.out.println("employee.toString(): " + employee.toString());

		String name = "mohamed test";
		employee.setFirstName(name);

		try
		{
		    Thread.sleep(10 * 1000);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}

		mockMvc.perform(
				post("/employee/save")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", String.valueOf(employee.getId()))
				.param("number", String.valueOf(employee.getNumber()))
				.param("firstName", String.valueOf(employee.getFirstName()))
				.param("lastName", String.valueOf(employee.getLastName()))
				.param("jobTitle", String.valueOf(employee.getJobTitle()))
				.param("email", String.valueOf(employee.getEmail()))
				.param("extension", String.valueOf(employee.getExtension()))
				//.param("hireDate", String.valueOf(employee.getHireDate())) // Marked as transient in entity
				.param("office", String.valueOf(employee.getOffice().getId()))
				.param("reportsTo", String.valueOf(employee.getReportsTo().getId()))
			)
			.andExpect(model().hasNoErrors())
			.andExpect(model().attribute("employees", hasItem(notNullValue())))
			.andExpect(model().attribute("offices", hasItem(notNullValue())))

			.andExpect(model().attribute("employee",
					allOf(
							hasProperty("id", equalTo(employeeId)),
							hasProperty("firstName", equalTo(name))
					)
			))
			;
		try
		{
		    Thread.sleep(10 * 1000);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
    }



    @Ignore
    @Test
	public void testDummyService_SpyShouldReturnDefinedValue() throws UnsupportedEncodingException, Exception {

    	//To use spy, the Container should return a real object

    	DummyService spyDummyService = spy(dummyService);
    	when(spyDummyService.getData("wassup")).thenReturn("from spy wassup");
    	String ret = spyDummyService.getData("wassup");
    	assertThat(ret, equalTo("from spy wassup"));
    	System.out.println("spyDummyService returned: " + ret);

    	URI url = UriComponentsBuilder.fromUriString("/test/testDummyService").pathSegment("wassup").build().encode().toUri();

    	String asString = mockMvc.perform(get(url)).andReturn().getResponse().getContentAsString();
    	System.out.println("--------- getContentAsString ----------");
    	System.out.println(asString);

    	mockMvc.perform(get(url))
    	.andExpect(status().isOk())
        .andExpect(content().string("from service wassup")); // Because the controller uses the real DummyService
    }


    @Ignore
    @Test
	public void testDummyBean_MockShouldReturnDefinedValue() throws UnsupportedEncodingException, Exception {

    	//To use mock, the Container should return a mock object (ideally defined in testContext.xml file)

    	Mockito.reset(dummyBean);
    	when(dummyBean.getData("wassup")).thenReturn("mock wassup");
    	//getData will be called in the controller method under test

    	URI url = UriComponentsBuilder.fromUriString("/test/testDummyBean").build().encode().toUri();

    	String asString = mockMvc.perform(get(url)).andReturn().getResponse().getContentAsString();
    	System.out.println("--------- getContentAsString ----------");
    	System.out.println(asString);

    	mockMvc.perform(get(url))
    	.andExpect(status().isOk())
        .andExpect(content().string("mock wassup"));

    }

    @Ignore
    @Test
    public void testOrderSearch_ShouldReturnOrderSearchViewAndEmptyOrder() throws Exception{

    	mockMvc.perform(get("/test/testOrderSearch"))
        .andExpect(status().isOk())
        .andExpect(view().name("order-search"))
        //.andExpect(forwardedUrl("/WEB-INF/views/tiles/order-search.jsp"));

        .andExpect(model().attribute("order", notNullValue()))
        .andExpect(model().attribute("order", allOf(
				hasProperty("id", nullValue()),
				hasProperty("number", nullValue()),
				hasProperty("status", nullValue()),
				hasProperty("customer", nullValue())
		)))
        .andExpect(model().attribute("customers", hasItem(notNullValue())));

    	/*
        .andExpect(model().attribute("todos", hasItem(
                allOf(
                        hasProperty("id", equalTo(2L)),
                        hasProperty("description", equalTo("Lorem ipsum")),
                        hasProperty("title", equalTo("Bar"))
                )
        )));
        */

    }

    @Ignore
	@Test
	public void testEditOrder_ShouldReturnAnOrderAndCustomers() throws Exception {

		mockMvc.perform(
					get("/test/testEditOrder/{orderId}", 1)
				)
				.andExpect(status().isOk())
				.andExpect(view().name("order-edit"))

				.andExpect(model().attribute("customers", hasItem(notNullValue())))
				.andExpect(model().attribute("order", notNullValue()))

				.andExpect(model().attribute("order",
						allOf(
								hasProperty("id", equalTo(1)),
								hasProperty("number", greaterThan(0))
						)
				));

    }

	@Ignore
	@Test
	public void testSaveOrder_OrderValidatoinShouldFail() throws Exception{

		Order order = new Order();
		order.setId(1);
		order.setNumber(22);

		order.setOrderDate(new Date());
		order.setRequiredDate(new Date());
		order.setShippedDate(new Date());

		order.setComments("myComments");
		order.setStatus("myStatus too long too long too long");

		order.setCustomer(null);
		order.setOrderDetails(null);

		//String.valueOf(null); throws exception

		mockMvc.perform(
					post("/test/testSaveOrder")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("id", String.valueOf(order.getId()))
					.param("number", String.valueOf(order.getNumber()))

					.param("orderDate", String.valueOf(order.getOrderDate()))
					.param("requiredDate", String.valueOf(order.getRequiredDate()))
					.param("shippedDate", String.valueOf(order.getShippedDate()))

					.param("status", String.valueOf(order.getStatus()))
					.param("comments", String.valueOf(order.getComments()))
				)
				.andExpect(model().attributeHasFieldErrors("order", "status"))
				.andExpect(model().attributeHasFieldErrors("order", "customer"))
				;
	}


    @Ignore
    @Test
    public void testWebAppContextIsLoaded() {
		ServletContext servletContext = wac.getServletContext();
        Assert.assertNotNull(servletContext);

        Assert.assertTrue(servletContext instanceof MockServletContext);

        System.out.println("======= Bean def start ======== ");
        for (String beanName : wac.getBeanDefinitionNames()) {
                System.out.println("Bean Name: " + beanName);
                System.out.println("Bean " + wac.getBean(beanName));
        }
        System.out.println("======= Bean def end ======== ");
	}


}

/*
ExampleApplicationContext 	=>		exampleApplicationContext.xml
WebAppContext 				=>		exampleApplicationContext-web.xml
PersistenceContext 			=>		exampleApplicationContext-persistence.xml
TestContext					=>		testContext.xml
*/

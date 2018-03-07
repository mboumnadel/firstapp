package com.med.firstapp.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Date;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriComponentsBuilder;

import com.med.firstapp.model.Employee;
import com.med.firstapp.model.Order;
import com.med.firstapp.service.DummyBean;
import com.med.firstapp.service.DummyService;
import com.med.firstapp.service.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)

//@ContextConfiguration(locations = {"classpath:testContext.xml", "classpath:exampleApplicationContext-web.xml"})

//@ContextConfiguration(locations = {"classpath:spring-servlet.xml"})
// classpath points to src/main/resources or src/test/resources

@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/applicationContext.xml",
		"file:src/main/webapp/WEB-INF/applicationContext-test.xml",
		"file:src/main/webapp/WEB-INF/applicationContext-persistence.xml",
		"file:src/main/webapp/WEB-INF/applicationContext-web.xml"
		})


@WebAppConfiguration // will load WebApplicationContext
//we load WebApplicationContext and MockServletContext using the @WebAppConfiguration annotation and inject their instances using @Autowired.

public class TestControllerTest {

	private MockMvc mockMvc;

    //@Autowired
    //private TodoService todoServiceMock;

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

    @Before
    public void setUp() {

       // Mockito.reset(todoServiceMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
	public void testEmployeeSave() throws UnsupportedEncodingException, Exception {

    	long threadId = Thread.currentThread().getId();
        System.out.println("Thread # " + threadId + " in testEmployeeSave");

    	System.out.println("---------- testEmployeeSave ---------");

    	int employeeId = 49;
		Employee employee = employeeService.findById(employeeId);
		String name = "mohamed test";
		employee.setFirstName(name);

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
    }

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
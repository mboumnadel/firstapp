package com.med.firstapp.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.web.FilterChainProxy;
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
import org.springframework.web.context.WebApplicationContext;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = {
		"classpath:applicationContext.xml",
		"classpath:applicationContext-test.xml",
		"classpath:applicationContext-persistence-test.xml",
		"classpath:applicationContext-web.xml",
		"classpath:applicationContext-security.xml"
		})

@WebAppConfiguration
@TestExecutionListeners(
	listeners = {
				  ServletTestExecutionListener.class
				, DirtiesContextBeforeModesTestExecutionListener.class
				, DependencyInjectionTestExecutionListener.class
				, DirtiesContextTestExecutionListener.class
				, SqlScriptsTestExecutionListener.class
				,TransactionDbUnitTestExecutionListener.class
				,WithSecurityContextTestExecutionListener.class
				}
)

//WithSecurityContextTestExcecutionListener will populate the SecurityContextHolder prior to running our tests.
//After the test is done, it will clear out the SecurityContextHolder.

public class ITSecurityTestControllerTest {

	private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    protected MockServletContext mockServletContext;

    @Autowired
    FilterChainProxy springSecurityFilterChain;

    @Before
    public void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
        		.addFilters(springSecurityFilterChain)
        		.apply(springSecurity())
        		.build();
    }

    @Test
    public void testUnsecured() throws Exception{
    	System.out.println("---------- testUnsecured @TEST Start ---------");

    	mockMvc.perform(
				get("/test/testUnsecured")
				.with(user("adminato").password("PaSS").roles("USER","ADMIN"))
			)
	    	.andExpect(authenticated())
	    	.andExpect(status().isOk())
			.andDo(print())
			;

    	System.out.println("---------- testUnsecured @TEST End ---------");
    	//Configuration :
    	//"classpath:applicationContext-security.xml"
    	//mockMvc = MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity()).build();
    	// Authentication is populated and Controller is accessible

    }

    @Test
    public void testPreAuthorizeAuthenticated() throws Exception{
    	System.out.println("---------- testPreAuthorizeAuthenticated @TEST Start ---------");

    	mockMvc.perform(
				get("/test/testPreAuthorizeAuthenticated")
			)
	    	.andExpect(unauthenticated())
	    	.andExpect(status().is3xxRedirection())
	        .andExpect(redirectedUrlPattern("http*://**/login"))
			.andDo(print())
			;

    	System.out.println("---------- testPreAuthorizeAuthenticated @TEST End ---------");
    	//Configuration :
    	//"classpath:applicationContext-security.xml"
    	//mockMvc = MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity()).build();
    	// Authentication is NOT populated and Controller is NO accessible
    	//redirect to http://localhost/login
    }

    @Test
    public void testPreAuthorizeAuthenticatedWithUser() throws Exception{
    	System.out.println("---------- testPreAuthorizeAuthenticatedWithUser @TEST Start ---------");

    	mockMvc.perform(
				get("/test/testPreAuthorizeAuthenticated")
				.with(user("adminato").password("PaSS").roles("USER","ADMIN"))
			)
    		.andExpect(authenticated().withUsername("adminato").withRoles("USER","ADMIN"))
    		.andExpect(status().isOk())
			.andDo(print())
			;

    	System.out.println("---------- testPreAuthorizeAuthenticatedWithUser @TEST End ---------");
    	//Configuration :
    	//"classpath:applicationContext-security.xml"
    	//mockMvc = MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity()).build();
    	// Authentication is populated and Controller is accessible
    }

    @Test
    public void testPreAuthorizeAuthenticatedWithUserDetails() throws Exception{
    	System.out.println("---------- testPreAuthorizeAuthenticatedWithUserDetails @TEST Start ---------");

    	UserDetails userDetails = new org.springframework.security.core.userdetails.User(
    	          "auser",
    	          "apassword",
    	          true,
    	          true,
    	          true,
    	          true,
    	          Arrays.asList(new SimpleGrantedAuthority("USER"), new SimpleGrantedAuthority("ADMIN"))
    	          );

    	mockMvc.perform(
				get("/test/testPreAuthorizeAuthenticated")
				.with(user(userDetails))
			)
    		.andExpect(authenticated().withUsername("auser").withAuthorities(Arrays.asList(new SimpleGrantedAuthority("USER"), new SimpleGrantedAuthority("ADMIN"))))
        	.andExpect(status().isOk())
			.andDo(print())
			;

    	System.out.println("---------- testPreAuthorizeAuthenticatedWithUserDetails @TEST End ---------");
    	//Configuration :
    	//"classpath:applicationContext-security.xml"
    	//mockMvc = MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity()).build();
    	// Authentication is populated and Controller is accessible
    }

    @Test
    @WithUserDetails(value="admin")
    @DatabaseSetup("/userDetails.xml")
    public void testPreAuthorizeAuthenticatedWithUserDetailsAnnotation() throws Exception{
    	System.out.println("---------- testPreAuthorizeAuthenticatedWithUserAnnotation @TEST Start ---------");

    	System.out.println("springSecurityFilterChain: " + springSecurityFilterChain);

    	mockMvc.perform(get("/test/testPreAuthorizeAuthenticated"))
			.andExpect(authenticated().withUsername("admin"))
	        .andExpect(status().isOk())
	        .andDo(print())
			;

    	System.out.println("---------- testPreAuthorizeAuthenticatedWithUserAnnotation @TEST End ---------");
    	//Configuration :
    	//"classpath:applicationContext-security.xml"
    	//mockMvc = MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity()).build();
    	//@DatabaseSetup("/userDetails.xml")
    	//WithSecurityContextTestExecutionListener.class
    	// Authentication is populated and Controller is accessible
    	//user must exist
    }

    @Test
    @DatabaseSetup("/userDetails.xml")
    public void testLogin() throws Exception{
    	System.out.println("---------- testLogin @TEST Start ---------");

    	mockMvc.perform(formLogin("/login").user("admin").password("admin"))
    		.andExpect(authenticated().withUsername("admin"))
        	.andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"))
			.andDo(print())
			;

    	System.out.println("---------- testLogin @TEST End ---------");
    }

    @Test
    public void testLogout() throws Exception{
    	System.out.println("---------- testLogout @TEST Start ---------");

    	mockMvc.perform(logout("/logout"))
    	.andExpect(unauthenticated())
    	.andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/login?logout"))
    	.andDo(print())
    	;

    	System.out.println("---------- testLogout @TEST End ---------");
    }

}

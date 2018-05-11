package com.med.firstapp.controller;

import java.security.Principal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = {"/"})
public class HomeController {

	@RequestMapping(method = RequestMethod.GET)
	public String welcomePage(ModelMap model, Principal principal, Authentication authentication, HttpServletRequest request) {

		System.out.println("welcome Page controller");
		System.out.println("welcome principal.getName " + principal.getName());


		Boolean inRole = request.isUserInRole("ADMIN");
		System.out.println("inRole ADMIN :" + inRole);

		//http://www.baeldung.com/spring-security-expressions-basic
		//http://www.baeldung.com/spring-security-expressions

		//@AuthenticationPrincipal User user

		//Subject s = null;
		//principal.implies(s);

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		System.out.println("authorities :" + authorities);


		Object credentials = authentication.getCredentials();
		System.out.println("credentials :" + credentials);


		Object details = authentication.getDetails();
		System.out.println("details :" + details);

		String name = authentication.getName();
		System.out.println("name :" + name);



		return "welcome";
	}

	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public String loginPage(ModelMap model) {

		System.out.println("loginPage controller");
		return "login";
	}
}

//@RequestMapping(value = {}) <=> @RequestMapping

/*
No @RequestMapping on class level
	@RequestMapping 							=> Catch All for every path level
	@RequestMapping("") 						=> NOTHING
	@RequestMapping("/") 						=> NOTHING						OR	/ when web.xml dispatcher uses (/* or maps root context)

	@RequestMapping(value = {"mylogin1"}) 		=> /mylogin1
	@RequestMapping(value = {"/mylogin2"}) 		=> /mylogin2
*/



/*
@RequestMapping("/") on class level
	@RequestMapping("") 						=> NOTHING						OR	/ when web.xml dispatcher uses (/* or maps root context)
	@RequestMapping(value = {"/"} 				=> NOTHING						OR	/ when web.xml dispatcher uses (/* or maps root context)
	@RequestMapping 							=> NOTHING						OR	/ when web.xml dispatcher uses (/* or maps root context)

	@RequestMapping(value = {"mylogin1"}) 		=> /mylogin1
	@RequestMapping(value = {"/mylogin2"}) 		=> /mylogin2
*/

/*
@RequestMapping("/abc") on class level
	@RequestMapping or @RequestMapping("") 		=> /abc and /abc/
	@RequestMapping(value = {"/"} 				=> /abc/

	@RequestMapping(value = {"mylogin1"})		=> /abc/mylogin1
	@RequestMapping(value = {"/mylogin2"}) 		=> /abc/mylogin2
*/

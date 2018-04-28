package com.med.firstapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = {"/"})
public class HomeController {

	@RequestMapping(method = RequestMethod.GET)
	public String welcomePage(ModelMap model) {

		System.out.println("welcome Page controller");
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

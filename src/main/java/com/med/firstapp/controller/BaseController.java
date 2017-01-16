package com.med.firstapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/a")
public class BaseController {

	private static int counter = 0;

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model) {

		model.addAttribute("message", "Welcome / ");
		model.addAttribute("counter", ++counter);
		//logger.debug("[welcome] counter : {}", counter);

		// Spring uses InternalResourceViewResolver and return back index.jsp
		return "base";

	}

	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public String welcomeName(@PathVariable String name, ModelMap model) {

		model.addAttribute("message", "Welcome {name} : " + name);
		model.addAttribute("counter", ++counter);
		//logger.debug("[welcomeName] counter : {}", counter);
		return "base";

	}


	@RequestMapping(value = "/AAA/**", method = RequestMethod.GET)
	public String catchall(ModelMap model) {

		model.addAttribute("message", "Welcome /** : ");
		model.addAttribute("counter", ++counter);
		return "base";

	}
	
}

package com.cap.org.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cap.org.service.LoginService;
import com.cap.org.service.LoginServiceImpl;

@Controller
public class LoginController {
	
//	@Autowired
//	private LoginService loginService;
	
	@Autowired
	private LoginServiceImpl loginService;
	
	
	@RequestMapping("/login")
	public ModelAndView checkLogin(@RequestParam("name") String name, 
			@RequestParam("id") int customerId) {
		
		ModelAndView mov = new ModelAndView();
		
		System.out.println("Checking the Valid Customer : "+name+" With Id = "+customerId);
		
		if(loginService.checkLogin(name, customerId)) {
			mov.setViewName("login-successful");
			mov.addObject("name", name);
		}
		else {
			mov.setViewName("login");
			mov.addObject("name", "Login Unsuccessful Try Again");
		}
		
		return mov;
		
	}

}

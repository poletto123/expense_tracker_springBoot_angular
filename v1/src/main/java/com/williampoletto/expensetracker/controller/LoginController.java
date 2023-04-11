package com.williampoletto.expensetracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	@GetMapping("/forgotPassword")
	public String forgotPassword() {
		return "forgot-password";
	}
	
	@GetMapping("/newUser")
	public String newUser() {
		return "new-user";
	}
	 
}

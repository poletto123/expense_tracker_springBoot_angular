package com.williampoletto.expensetracker.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.williampoletto.expensetracker.entity.User;
import com.williampoletto.expensetracker.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/listUsers")
	public String listUsers(Model model) {
		
		Collection<User> users = userService.findAll();
		
		model.addAttribute("users", users);
		
		return "list-users";
	}
	
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("userId") int id) {
		
		userService.deleteById(id);
		
		return "redirect:/list-users";
	}
	
	@GetMapping("/searchUser")
	public String searchUser(@RequestParam("username") String name, Model model) {
		
		Collection<User> users = userService.searchBy(name);
		
		model.addAttribute("users", users);
		
		return "/expenses/list-users";
	}
	
	
	
}

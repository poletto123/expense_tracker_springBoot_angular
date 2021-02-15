package com.williampoletto.expensetracker.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.williampoletto.expensetracker.entity.Expense;
import com.williampoletto.expensetracker.entity.UserDetailsImpl;
import com.williampoletto.expensetracker.service.ExpenseService;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@GetMapping
	public String list(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
		
		Set<Expense> expenses = expenseService.findAll(userDetails.getUserId());
		
		model.addAttribute("expenses", expenses);
		
		model.addAttribute("newExpense", new Expense());
		
		return "expenses";
	}
	
	@PostMapping("/save")
	public String saveExpense(@AuthenticationPrincipal UserDetailsImpl userDetails,
						@ModelAttribute("expense") Expense expense,
						RedirectAttributes attributes) {
		
		expenseService.save(userDetails.getUserId(), expense);
		
		attributes.addFlashAttribute("message", "The expense was saved successfully");
		
		return "redirect:/expenses";
	}

	@PostMapping("/delete")
	public String delete(@ModelAttribute("expense") Expense expense, RedirectAttributes attributes) {
		
		expenseService.delete(expense);
		
		attributes.addFlashAttribute("message", "The expense was deleted successfully");
		
		return "redirect:/expenses";
	}
}

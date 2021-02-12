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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.williampoletto.expensetracker.entity.Expense;
import com.williampoletto.expensetracker.entity.UserDetailsImpl;
import com.williampoletto.expensetracker.service.ExpenseService;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@GetMapping("/list")
	public String list(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
		
		Set<Expense> expenses = expenseService.findAll(userDetails.getUserId());
		
		model.addAttribute("expenses", expenses);
		
		return "expenses";
	}
	
	@GetMapping("/showAddForm")
	public String showFormForAdd(Model model) {
		
		Expense expense = new Expense();
		
		model.addAttribute("expense", expense);
		
		return "/expense-form";
	}
	
	@PostMapping("/showUpdateForm")
	public String showFormForUpdate(@ModelAttribute Expense expense, RedirectAttributes attributes) {
		
//		Expense expense = expenseService.findById(id);
		
		attributes.addFlashAttribute("expense", expense);
		
		return "/expense-form";
		
	}
	
	@PostMapping("/save")
	public String saveExpense(@AuthenticationPrincipal UserDetailsImpl userDetails,
						@ModelAttribute("expense") Expense expense,
						RedirectAttributes attributes) {
		
		expenseService.save(userDetails.getUserId(), expense);
		
		attributes.addFlashAttribute("message", "The expense was saved successfully");
		
		return "redirect:/expenses/list";
	}

	@GetMapping("/delete")
	public String delete(	@RequestParam("expenseId") int expenseId,	RedirectAttributes attributes) {
		
		expenseService.delete(expenseId);
		
		attributes.addFlashAttribute("message", "The expense was deleted successfully");
		
		return "redirect:/expenses/list";
	}
}

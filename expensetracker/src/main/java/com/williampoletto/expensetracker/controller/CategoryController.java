package com.williampoletto.expensetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.williampoletto.expensetracker.entity.Category;
import com.williampoletto.expensetracker.entity.UserDetailsImpl;
import com.williampoletto.expensetracker.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public String list(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
		
		List<Category> categories = categoryService.findAll(userDetails.getUserId());
		
		model.addAttribute("categories", categories);
		
		model.addAttribute("newCategory", new Category());
		
		return "categories";
	}

	
	@PostMapping("/save")
	public String updateExpense(@AuthenticationPrincipal UserDetailsImpl userDetails,
						@ModelAttribute("category") Category category,
						RedirectAttributes attributes) {
		
		categoryService.save(userDetails.getUserId(), category);
		
		attributes.addFlashAttribute("message", "The category was saved successfully");
		
		return "redirect:/categories";
	}

	@PostMapping("/delete")
	public String delete(@ModelAttribute("category")Category category, RedirectAttributes attributes) {
		
		categoryService.delete(category);
		
		attributes.addFlashAttribute("message", "The category was deleted successfully");
		
		return "redirect:/categories";
	}

}

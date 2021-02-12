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

import com.williampoletto.expensetracker.entity.Category;
import com.williampoletto.expensetracker.entity.UserDetailsImpl;
import com.williampoletto.expensetracker.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/list")
	public String list(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
		
		Set<Category> categories = categoryService.findAll(userDetails.getUserId());
		
		model.addAttribute("categories", categories);
		
		return "categories";
	}
	
	@GetMapping("/showAddForm")
	public String showFormForAdd(Model model) {
		
		Category category = new Category();
		
		model.addAttribute("category", category);
		
		return "/category-form";
	}
	
	@PostMapping("/showUpdateForm")
	public String showFormForUpdate(@RequestParam("categoryId") int categoryId,
								@RequestParam("categoryName") String categoryName,
								Model model) {
		
		Category category = new Category(categoryId, categoryName);
		
		model.addAttribute("category", category);
		
		return "/category-form";
		
	}
	
	@PostMapping("/save")
	public String updateExpense(@AuthenticationPrincipal UserDetailsImpl userDetails,
						@ModelAttribute("category") Category category,
						RedirectAttributes attributes) {
		
		categoryService.save(userDetails.getUserId(), category);
		
		attributes.addFlashAttribute("message", "The category was saved successfully");
		
		return "redirect:/categories/list";
	}

	@GetMapping("/delete")
	public String delete(@AuthenticationPrincipal UserDetailsImpl userDetails,
					@RequestParam("category")Category category,
					RedirectAttributes attributes) {
		
		categoryService.delete(userDetails.getUserId(), category);
		
		attributes.addFlashAttribute("message", "The category was deleted successfully");
		
		return "redirect:/categories/list";
	}

}

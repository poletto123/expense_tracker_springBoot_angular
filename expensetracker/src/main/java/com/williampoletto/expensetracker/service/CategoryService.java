package com.williampoletto.expensetracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.williampoletto.expensetracker.entity.Category;
import com.williampoletto.expensetracker.entity.User;
import com.williampoletto.expensetracker.repository.CategoryRepository;

@Service 
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findAll(int userId) {
			
		List<Category> userCategories = categoryRepository.findAllWithUserId(userId);
		List<Category> fixedCategories = categoryRepository.findAllWithUserIdNull();
		
		List<Category> allCategories = new ArrayList<>();;

		userCategories.stream()
							.forEach(category -> allCategories.add(category));
		fixedCategories.stream()
							.forEach(category -> allCategories.add(category));
		
		return allCategories;
	}
	
	public Category findById(int id) {
		
		Optional<Category> category = categoryRepository.findById(id);

		category.orElseThrow(() -> new RuntimeException("Did not find category id - " + id));

		return category.get();
	}
	
	public void save(int userId, Category category) {
		
		category.setUser(new User(userId));
		
		categoryRepository.save(category);
	}
	
	public void delete(Category category) {
		
		categoryRepository.delete(category);
	}
	
}

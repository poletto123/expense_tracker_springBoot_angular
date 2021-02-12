package com.williampoletto.expensetracker.service;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.williampoletto.expensetracker.entity.Category;
import com.williampoletto.expensetracker.entity.User;
import com.williampoletto.expensetracker.repository.CategoryRepository;

@Service 
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Set<Category> findAll(int userId) {
			
		Set<Category> userCategories = categoryRepository.findAllWithUserId(userId);
		Set<Category> fixedCategories = categoryRepository.findAllWithUserIdNull();
		
		Set<Category> allCategories = new LinkedHashSet<>();

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
	
	public void delete(int userId, Category category) {
		
		category.setUser(new User(userId));

		categoryRepository.delete(category);
	}
	
}

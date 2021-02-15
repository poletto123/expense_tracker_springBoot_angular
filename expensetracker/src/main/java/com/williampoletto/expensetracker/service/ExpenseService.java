package com.williampoletto.expensetracker.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.williampoletto.expensetracker.entity.Expense;
import com.williampoletto.expensetracker.entity.User;
import com.williampoletto.expensetracker.repository.ExpenseRepository;

@Service 
public class ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;
	
	public Set<Expense> findAll(int userId) {
		return expenseRepository.findAll(userId);
	}
	
	public Expense findById(int id) {
		
		Optional<Expense> expense = expenseRepository.findById(id);

		expense.orElseThrow(() -> new UsernameNotFoundException("Expense not found"));

		return expense.get();
	}
	
	public void save(int userId, Expense expense) {
			
		expense.setUser(new User(userId));
		
		expenseRepository.save(expense);
	}
	
	public void delete(Expense expense) {
		
		expenseRepository.delete(expense);
	}
	
	public Set<Expense> searchBy(int userId, String name) {
		
		if (name != null && (name.trim().length() > 0)) {
			return expenseRepository.findByDescriptionIgnoreCase(name);
		} else {
			return findAll(userId);
		}
	}
	
}

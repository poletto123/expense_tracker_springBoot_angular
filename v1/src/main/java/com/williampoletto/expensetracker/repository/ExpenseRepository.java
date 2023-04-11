package com.williampoletto.expensetracker.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.williampoletto.expensetracker.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

	@Query("SELECT e FROM Expense e LEFT JOIN FETCH e.categories where e.user.id = ?1")
	Set<Expense> findAll(int id);
	
	Set<Expense> findByDescriptionIgnoreCase(String name);
	
	
}

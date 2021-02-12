package com.williampoletto.expensetracker.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.williampoletto.expensetracker.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("SELECT c FROM Category c where c.user.id = NULL")
	Set<Category> findAllWithUserIdNull();
	
	@Query("SELECT c FROM Category c where c.user.id = ?1")
	Set<Category> findAllWithUserId(int userId);

}

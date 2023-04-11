package com.williampoletto.expensetracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.williampoletto.expensetracker.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("SELECT c FROM Category c where c.user.id = NULL")
	List<Category> findAllWithUserIdNull();
	
	@Query("SELECT c FROM Category c where c.user.id = ?1")
	List<Category> findAllWithUserId(int userId);

}

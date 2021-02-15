package com.williampoletto.expensetracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.williampoletto.expensetracker.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
 
	Optional<User> findByUsername(String username);
	
	@Query("SELECT u FROM User u JOIN FETCH u.roles where u.username = ?1")
	Optional<User> findByUsernameWithRoles(String username);
	
	List<User> findByUsernameIgnoreCase(String username);

	void deleteById(int id);
	
}

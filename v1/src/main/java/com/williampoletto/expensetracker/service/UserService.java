package com.williampoletto.expensetracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.williampoletto.expensetracker.entity.User;
import com.williampoletto.expensetracker.repository.UserRepository;

@Service 
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById(int id) {
		
		Optional<User> User = userRepository.findById(id);

		User.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		return User.get();
	}
	
	public void save(User user) {
		userRepository.save(user);
	}
	
	public void deleteById(int id) {
		userRepository.deleteById(id);
	}
	
	public List<User> searchBy(String name) {
		
		if (name != null && (name.trim().length() > 0)) {
			return userRepository.findByUsernameIgnoreCase(name);
		} else {
			return findAll();
		}
	}
	
}

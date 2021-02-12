package com.williampoletto.expensetracker.entity;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


// Class was created to satisfy loadUserByUsername in UserDetailsServiceImpl
// Purpose is to transform User into UserDetails

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private int userId;
	private boolean isActive;
	private Collection<GrantedAuthority> authorities;
	
	public UserDetailsImpl(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.userId = user.getId();
		this.isActive = user.isActive();
		this.authorities = user.getRoles().stream()
 				.map(role -> new SimpleGrantedAuthority(role.getName()))
 				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isActive;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}

package com.school.security;

import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.school.Entity.User;

public class CustomUserDetails implements UserDetails{
	
	private User user;
	
	// constructor
	
	public CustomUserDetails(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
			
		SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole());
		return List.of(grantedAuthority);
	}

	@Override
	public @Nullable String getPassword() {
		
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getEmail();
	}
	
	public User getUser() {
	    return user;
	}
	
}

package com.school.security;

import java.util.Collection;
import java.util.Collections;
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
//		System.out.println("customuserdetails ka user" +user.getEmail());
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println("granded authority run..");
	     List<SimpleGrantedAuthority> singletonList = Collections.singletonList(
	            new SimpleGrantedAuthority(user.getRole())
	    );
	    System.out.println(singletonList);
	     return singletonList;
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
